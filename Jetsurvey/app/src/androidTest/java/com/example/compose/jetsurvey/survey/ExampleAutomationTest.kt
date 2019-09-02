package com.example.compose.jetsurvey.survey

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.compose.jetsurvey.MainActivity
import org.junit.Rule
import org.junit.Test

class ExampleAutomationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun guestSignIn() {
        composeTestRule.onNodeWithText("Sign in as guest").performClick()
    }
}
