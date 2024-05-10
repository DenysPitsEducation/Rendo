package com.rendo.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

abstract class DiffFlow<T>(
    originalFlow: Flow<List<T>>,
    triggerOnInit: Boolean = false
) : Flow<DiffFlow.ListUpdate<T>> {
    private val oldFlow = MutableStateFlow<List<T>?>(null)

    private val diffFlow: Flow<ListUpdate<T>> = originalFlow
        .map { newList ->
            val oldList = oldFlow.firstOrNull() ?: newList.takeIf { !triggerOnInit }.orEmpty()

            val updates = mutableListOf<ItemUpdate<T>>()

            newList.forEach { newItem ->
                val oldItem = oldList.find { oldItem ->
                    areItemsTheSame(oldItem, newItem)
                }
                when {
                    oldItem == null -> {
                        updates.add(ItemUpdate.Added(newItem))
                    }
                    !areContentsTheSame(oldItem, newItem) -> {
                        updates.add(ItemUpdate.Changed(oldItem, newItem))
                    }
                }
            }

            oldList.forEach { oldItem ->
                val newItem = newList.find { newItem ->
                    areItemsTheSame(oldItem, newItem)
                }
                if (newItem == null) {
                    updates.add(ItemUpdate.Removed(oldItem))
                }
            }

            ListUpdate(
                oldList = oldList,
                newList = newList,
                itemUpdates = updates
            )
        }
        .onEach { oldFlow.value = it.newList }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    override suspend fun collect(collector: FlowCollector<ListUpdate<T>>) {
        collector.emitAll(diffFlow)
    }

    sealed interface ItemUpdate<T> {
        data class Added<T>(
            val newItem: T
        ) : ItemUpdate<T>

        data class Removed<T>(
            val oldItem: T
        ) : ItemUpdate<T>

        data class Changed<T>(
            val oldItem: T,
            val newItem: T
        ) : ItemUpdate<T>
    }

    data class ListUpdate<T>(
        val oldList: List<T>,
        val newList: List<T>,
        val itemUpdates: List<ItemUpdate<T>>
    )
}

fun <T> Flow<List<T>>.toDiffFlow(
    areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    triggerOnInit: Boolean = false
) = object : DiffFlow<T>(this, triggerOnInit) {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame.invoke(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame.invoke(oldItem, newItem)
}

@Composable
expect fun <T> StateFlow<T>.collectAsStateWithLifecycle(): State<T>
