package com.flatrocktech.famousquotequiz.feature.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.flatrocktech.famousquotequiz.core.theme.FamousQuoteQuizTheme
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.app_name
import famousquotequiz.shared.generated.resources.branding_subtitle
import org.jetbrains.compose.resources.stringResource

@Composable
fun BrandingHeader() {
    val colorScheme = MaterialTheme.colorScheme
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color = colorScheme.primary, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "\u201C",
                style = TextStyle(
                    fontSize = 36.sp,
                    color = colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.offset(y = (12).dp)
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(Res.string.app_name),
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary,
                letterSpacing = (-0.64).sp
            )
        )

        Text(
            text = stringResource(Res.string.branding_subtitle),
            style = TextStyle(
                fontSize = 14.sp,
                color = colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun BrandingHeaderPreview() {
    FamousQuoteQuizTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            BrandingHeader()
        }
    }
}
