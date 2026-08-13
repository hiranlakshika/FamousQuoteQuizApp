package com.flatrocktech.famousquotequiz.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import com.flatrocktech.famousquotequiz.core.theme.Dimensions

@Composable
fun InsightOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String?,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag(InsightOutlinedTextFieldTags.ROOT)
    ) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = Dimensions.SpacingExtraSmall)
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(InsightOutlinedTextFieldTags.TEXT_FIELD),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorScheme.outline.copy(alpha = 0.7f)
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = RoundedCornerShape(Dimensions.RadiusSmall),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.secondary,
                unfocusedBorderColor = colorScheme.outline.copy(alpha = 0.5f),
                errorBorderColor = colorScheme.error,
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                errorContainerColor = colorScheme.surface,
                cursorColor = colorScheme.secondary,
                focusedTextColor = colorScheme.onSurface,
                unfocusedTextColor = colorScheme.onSurface,
                errorTextColor = colorScheme.error
            )
        )

        AnimatedVisibility(
            visible = isError && errorMessage != null,
            enter = fadeIn(animationSpec = tween(150)) + expandVertically(animationSpec = tween(150)),
            exit = fadeOut(animationSpec = tween(150)) + shrinkVertically(animationSpec = tween(150))
        ) {
            Text(
                text = errorMessage ?: "",
                style = MaterialTheme.typography.labelMedium,
                color = colorScheme.error,
                modifier = Modifier
                    .padding(
                        start = Dimensions.SpacingExtraSmall,
                        top = Dimensions.SpacingExtraSmall
                    )
                    .testTag(InsightOutlinedTextFieldTags.ERROR_MESSAGE)
            )
        }
    }
}

object InsightOutlinedTextFieldTags {
    const val ROOT = "insight_outlined_text_field_root"
    const val TEXT_FIELD = "insight_outlined_text_field_input"
    const val ERROR_MESSAGE = "insight_outlined_text_field_error"
}
