package com.flatrocktech.famousquotequiz.feature.auth.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.core.presentation.components.InsightOutlinedTextField
import com.flatrocktech.famousquotequiz.core.theme.FamousQuoteQuizTheme
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.email_label
import famousquotequiz.shared.generated.resources.email_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    imeAction: ImeAction = ImeAction.Next
) {
    val colorScheme = MaterialTheme.colorScheme
    InsightOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = stringResource(Res.string.email_label),
        placeholder = stringResource(Res.string.email_placeholder),
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = if (error != null) colorScheme.error else colorScheme.outline,
                modifier = Modifier.size(Dimensions.IconSmall)
            )
        },
        isError = error != null,
        errorMessage = error,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        )
    )
}

@Preview
@Composable
fun EmailTextFieldPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(Dimensions.PaddingMedium)) {
            EmailTextField(
                value = "",
                onValueChange = {}
            )
        }
    }
}

@Preview
@Composable
fun EmailTextFieldErrorPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(Dimensions.PaddingMedium)) {
            EmailTextField(
                value = "invalid-email",
                onValueChange = {},
                error = "Please enter a valid email address."
            )
        }
    }
}
