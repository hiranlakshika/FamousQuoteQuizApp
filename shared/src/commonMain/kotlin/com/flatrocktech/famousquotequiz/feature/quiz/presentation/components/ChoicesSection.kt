package com.flatrocktech.famousquotequiz.feature.quiz.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.quiz_who_said
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChoicesSection(
    choices: List<String>,
    selectedIndex: Int?,
    correctIndex: Int?,
    isSubmitted: Boolean,
    onChoiceSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingMedium)
    ) {
        Text(
            text = stringResource(Res.string.quiz_who_said),
            style = MaterialTheme.typography.labelLarge,
            color = colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        choices.forEachIndexed { index, choice ->
            val state = when {
                isSubmitted && correctIndex != null && index == correctIndex -> ChoiceState.Correct
                isSubmitted && index == selectedIndex && selectedIndex != correctIndex -> ChoiceState.Incorrect
                !isSubmitted && index == selectedIndex -> ChoiceState.Selected
                else -> ChoiceState.Default
            }
            ChoiceButton(
                text = choice,
                state = state,
                onClick = { onChoiceSelected(index) }
            )
        }
    }
}
