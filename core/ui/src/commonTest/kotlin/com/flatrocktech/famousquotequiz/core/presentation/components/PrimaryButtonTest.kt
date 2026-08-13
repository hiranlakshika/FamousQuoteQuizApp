package com.flatrocktech.famousquotequiz.core.presentation.components

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class PrimaryButtonTest {

    @Test
    fun button_displays_text() = runComposeUiTest {
        setContent {
            PrimaryButton(
                text = "Click Me",
                onClick = {}
            )
        }

        onNodeWithText("Click Me").assertIsDisplayed()
    }

    @Test
    fun button_is_clickable() = runComposeUiTest {
        var clicked = false
        setContent {
            PrimaryButton(
                text = "Click Me",
                onClick = { clicked = true }
            )
        }

        onNodeWithTag(PrimaryButtonTags.BUTTON).performClick()
        assertTrue(clicked)
    }

    @Test
    fun button_is_disabled_when_enabled_is_false() = runComposeUiTest {
        setContent {
            PrimaryButton(
                text = "Click Me",
                onClick = {},
                enabled = false
            )
        }

        onNodeWithTag(PrimaryButtonTags.BUTTON).assertIsNotEnabled()
    }

    @Test
    fun button_shows_loading_indicator_and_is_disabled_when_loading() = runComposeUiTest {
        setContent {
            PrimaryButton(
                text = "Click Me",
                onClick = {},
                isLoading = true
            )
        }

        // Loading indicator should be displayed
        onNodeWithTag(PrimaryButtonTags.LOADING_INDICATOR).assertIsDisplayed()

        // Button should be disabled while loading
        onNodeWithTag(PrimaryButtonTags.BUTTON).assertIsNotEnabled()

        // Text should not be visible (or at least replaced by indicator)
        onNodeWithText("Click Me").assertDoesNotExist()
    }

    @Test
    fun button_is_enabled_when_not_loading_and_enabled_is_true() = runComposeUiTest {
        setContent {
            PrimaryButton(
                text = "Click Me",
                onClick = {},
                enabled = true,
                isLoading = false
            )
        }

        onNodeWithTag(PrimaryButtonTags.BUTTON).assertIsEnabled()
    }
}
