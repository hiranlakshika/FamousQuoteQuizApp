package com.flatrocktech.famousquotequiz.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import androidx.compose.ui.unit.sp
import com.flatrocktech.famousquotequiz.core.presentation.components.PrimaryButton
import com.flatrocktech.famousquotequiz.feature.auth.presentation.components.BrandingHeader
import com.flatrocktech.famousquotequiz.feature.auth.presentation.components.EmailTextField
import com.flatrocktech.famousquotequiz.feature.auth.presentation.components.PasswordTextField
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.login_title
import famousquotequiz.shared.generated.resources.no_account
import famousquotequiz.shared.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val colorScheme = MaterialTheme.colorScheme

    LaunchedEffect(state.isLoginSuccess) {
        if (state.isLoginSuccess) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        colorScheme.background
                    ),
                    center = Offset(Float.POSITIVE_INFINITY, 0f),
                    radius = 1400f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimensions.ScreenPadding, vertical = Dimensions.SpacingExtraLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = Dimensions.LoginCardMaxWidth)
                    .shadow(
                        elevation = Dimensions.ElevationLarge,
                        shape = RoundedCornerShape(Dimensions.RadiusMedium),
                        ambientColor = colorScheme.primary.copy(alpha = 0.06f),
                        spotColor = colorScheme.primary.copy(alpha = 0.06f)
                    ),
                shape = RoundedCornerShape(Dimensions.RadiusMedium),
                colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.ElevationNone)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimensions.PaddingLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimensions.PaddingLarge)
                ) {
                    BrandingHeader()

                    LoginForm(
                        state = state,
                        onIntent = viewModel::onIntent
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(Res.string.no_account),
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.onSurfaceVariant
                        )
                        TextButton(
                            onClick = {},
                            contentPadding = PaddingValues(Dimensions.SpacingZero)
                        ) {
                            Text(
                                text = stringResource(Res.string.sign_up),
                                style = MaterialTheme.typography.labelLarge,
                                color = colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginForm(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.PaddingMedium),
        modifier = Modifier.fillMaxWidth()
    ) {
        EmailTextField(
            value = state.email,
            onValueChange = { onIntent(LoginIntent.OnEmailChanged(it)) },
            error = state.emailError?.let { stringResource(it) },
            imeAction = ImeAction.Next
        )

        PasswordTextField(
            value = state.password,
            onValueChange = { onIntent(LoginIntent.OnPasswordChanged(it)) },
            error = state.passwordError?.let { stringResource(it) },
            onForgotPasswordClick = {},
            imeAction = ImeAction.Done
        )

        Spacer(Modifier.height(Dimensions.SpacingExtraSmall))

        PrimaryButton(
            text = stringResource(Res.string.login_title),
            onClick = { onIntent(LoginIntent.OnLoginClicked) },
            isLoading = state.isLoading
        )
    }
}
