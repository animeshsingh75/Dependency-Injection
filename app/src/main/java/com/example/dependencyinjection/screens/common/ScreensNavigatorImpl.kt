package com.example.dependencyinjection.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.common.dependencyInjection.activity.ActivityScope
import com.example.dependencyinjection.screens.common.viewsMvc.ScreensNavigator
import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsActivity
import com.example.dependencyinjection.screens.viewmodel.ViewModelActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigatorImpl @Inject constructor(private val activity: AppCompatActivity) : ScreensNavigator {

    override fun navigateBack() {
        activity.onBackPressedDispatcher.onBackPressed()
    }

    override fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }

    override fun toViewModel() {
        ViewModelActivity.start(activity)
    }
}