package com.flatrocktech.famousquotequiz.feature.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.content_desc_hide_password
import famousquotequiz.shared.generated.resources.content_desc_show_password
import famousquotequiz.shared.generated.resources.forgot_password
import famousquotequiz.shared.generated.resources.password_label
import famousquotequiz.shared.generated.resources.password_placeholder
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.flatrocktech.famousquotequiz.core.presentation.components.InsightOutlinedTextField
import com.flatrocktech.famousquotequiz.core.theme.FamousQuoteQuizTheme

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    onForgotPasswordClick: () -> Unit = {},
    imeAction: ImeAction = ImeAction.Done
) {
    val colorScheme = MaterialTheme.colorScheme
    var passwordVisible by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(0.dp), modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.password_label),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorScheme.onSurfaceVariant,
                    letterSpacing = 0.14.sp
                )
            )
            TextButton(
                onClick = onForgotPasswordClick,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(Res.string.forgot_password),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = colorScheme.secondary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        InsightOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = null,
            placeholder = stringResource(Res.string.password_placeholder),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = if (error != null) colorScheme.error else colorScheme.outline,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) stringResource(Res.string.content_desc_hide_password) else stringResource(
                            Res.string.content_desc_show_password
                        ),
                        tint = colorScheme.outline,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            isError = error != null,
            errorMessage = error,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            )
        )
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PasswordTextField(
                value = "",
                onValueChange = {}
            )
        }
    }
}

@Preview
@Composable
fun PasswordTextFieldErrorPreview() {
    FamousQuoteQuizTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PasswordTextField(
                value = "wrong password",
                onValueChange = {},
                error = "Invalid password"
            )
        }
    }
}
