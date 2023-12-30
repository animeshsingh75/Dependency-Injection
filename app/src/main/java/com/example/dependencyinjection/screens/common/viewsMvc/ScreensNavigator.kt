package com.example.dependencyinjection.screens.common.viewsMvc

import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsActivity

interface ScreensNavigator {
    fun navigateBack()

    fun toQuestionDetails(questionId: String)

    fun toViewModel()
}