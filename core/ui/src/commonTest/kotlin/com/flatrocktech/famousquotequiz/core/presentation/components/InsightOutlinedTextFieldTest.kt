package com.flatrocktech.famousquotequiz.core.presentation.components

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class InsightOutlinedTextFieldTest {

    @Test
    fun label_isDisplayed_whenProvided() = runComposeUiTest {
        setContent {
            InsightOutlinedTextField(
                value = "",
                onValueChange = {},
                label = "Test Label",
                placeholder = "Test Placeholder"
            )
        }

        onNodeWithText("Test Label").assertIsDisplayed()
    }

    @Test
    fun placeholder_isDisplayed() = runComposeUiTest {
        setContent {
            InsightOutlinedTextField(
                value = "",
                onValueChange = {},
                label = null,
                placeholder = "Test Placeholder"
            )
        }

        onNodeWithText("Test Placeholder").assertIsDisplayed()
    }

    @Test
    fun value_isDisplayed() = runComposeUiTest {
        setContent {
            InsightOutlinedTextField(
                value = "Current Value",
                onValueChange = {},
                label = null,
                placeholder = "Test Placeholder"
            )
        }

        onNodeWithTag(InsightOutlinedTextFieldTags.TEXT_FIELD).assertTextContains("Current Value")
    }

    @Test
    fun typing_triggers_onValueChange() = runComposeUiTest {
        var updatedValue = ""
        setContent {
            InsightOutlinedTextField(
                value = "",
                onValueChange = { updatedValue = it },
                label = null,
                placeholder = "Test Placeholder"
            )
        }

        onNodeWithTag(InsightOutlinedTextFieldTags.TEXT_FIELD).performTextInput("Hello")
        assertEquals("Hello", updatedValue)
    }

    @Test
    fun errorMessage_isDisplayed_whenIsErrorIsTrue() = runComposeUiTest {
        setContent {
            InsightOutlinedTextField(
                value = "",
                onValueChange = {},
                label = null,
                placeholder = "Test Placeholder",
                isError = true,
                errorMessage = "Error occurred"
            )
        }

        onNodeWithTag(InsightOutlinedTextFieldTags.ERROR_MESSAGE).assertIsDisplayed()
        onNodeWithText("Error occurred").assertIsDisplayed()
    }

    @Test
    fun errorMessage_isNotDisplayed_whenIsErrorIsFalse() = runComposeUiTest {
        setContent {
            InsightOutlinedTextField(
                value = "",
                onValueChange = {},
                label = null,
                placeholder = "Test Placeholder",
                isError = false,
                errorMessage = "Error occurred"
            )
        }

        onNodeWithTag(InsightOutlinedTextFieldTags.ERROR_MESSAGE).assertDoesNotExist()
    }
}
