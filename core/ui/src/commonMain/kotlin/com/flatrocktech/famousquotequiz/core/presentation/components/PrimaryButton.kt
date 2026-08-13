package com.flatrocktech.famousquotequiz.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.core.theme.FamousQuoteQuizTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true
) {
    val colorScheme = MaterialTheme.colorScheme
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.ButtonHeight)
            .testTag(PrimaryButtonTags.BUTTON),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(Dimensions.RadiusSmall),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary,
            disabledContainerColor = colorScheme.primary.copy(alpha = 0.6f),
            disabledContentColor = colorScheme.onPrimary.copy(alpha = 0.6f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = Dimensions.ElevationSmall,
            pressedElevation = Dimensions.ElevationNone
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(Dimensions.IconSmall)
                    .testTag(PrimaryButtonTags.LOADING_INDICATOR),
                color = colorScheme.onPrimary,
                strokeWidth = Dimensions.SpacingTiny
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = colorScheme.onPrimary
            )
        }
    }
}

object PrimaryButtonTags {
    const val BUTTON = "primary_button"
    const val LOADING_INDICATOR = "primary_button_loading_indicator"
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(Dimensions.PaddingMedium)) {
            PrimaryButton(
                text = "Login",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonLoadingPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(Dimensions.PaddingMedium)) {
            PrimaryButton(
                text = "Login",
                onClick = {},
                isLoading = true
            )
        }
    }
}
