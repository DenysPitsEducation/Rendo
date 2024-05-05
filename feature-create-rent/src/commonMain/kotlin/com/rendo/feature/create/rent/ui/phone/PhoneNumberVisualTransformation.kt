package com.rendo.feature.create.rent.ui.phone

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.withStyle

fun transformTextPhoneNumber(
    text: AnnotatedString,
    mask: String = "+380 (__) ___-__-__",
    editableSymbol: Char = '_',
    hintFontStyle: TextStyle? = null,
    textFontStyle: TextStyle? = null,
    editableLength: Int = mask.count { it == editableSymbol },
): TransformedText {
    val trimmedText = text.text.take(editableLength)
    val offsets: List<Int> = mask
        .mapIndexedNotNull { index, char ->
            index.takeIf { char == editableSymbol }
        }
        .plus(mask.length)
    var textIndex = 0
    var drawMaskAsText = true

    val annotatedString = buildAnnotatedString {
        mask.forEachIndexed { _, maskChar ->
            val textChar = trimmedText.getOrNull(textIndex)

            val hintSpanStyle = hintFontStyle?.toSpanStyle() ?: SpanStyle()
            val textSpanStyle = textFontStyle?.toSpanStyle() ?: SpanStyle()

            when {
                maskChar != editableSymbol && drawMaskAsText -> {
                    withStyle(hintSpanStyle) { append(maskChar) }
                }

                maskChar == editableSymbol && textChar != null -> {
                    withStyle(textSpanStyle) { append(textChar) }
                    textIndex++
                    drawMaskAsText = true
                }

                else -> {
                    withStyle(hintSpanStyle) { append(maskChar) }
                    drawMaskAsText = false
                }
            }
        }
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            println("Android offset: " + offset)
            println("My offset: " + offsets[offset])
            return offsets[offset]
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask
                .take(offset)
                .count { it == editableSymbol }
                .coerceIn(0, text.length)
        }
    }

    return TransformedText(
        text = annotatedString,
        offsetMapping = phoneNumberOffsetTranslator
    )
}