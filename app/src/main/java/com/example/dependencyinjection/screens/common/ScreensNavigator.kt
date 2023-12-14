package com.example.dependencyinjection.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: AppCompatActivity) {

    fun navigateBack() {
        activity.onBackPressedDispatcher.onBackPressed()
    }

    fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }
}