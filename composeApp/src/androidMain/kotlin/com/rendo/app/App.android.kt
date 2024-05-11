package com.rendo.app

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.app.di.getAllKoinModules
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    private val appInitializer: AppInitializer by inject()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
            androidContext(this@AndroidApp)
            modules(getAllKoinModules())
        }
        appInitializer.initialize()
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    App()
}
