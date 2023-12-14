package com.example.dependencyinjection.common.dependencyInjection

import com.example.dependencyinjection.questions.FetchQuestionDetailsUseCase
import com.example.dependencyinjection.questions.FetchQuestionsUseCase
import com.example.dependencyinjection.screens.common.dialogs.DialogsNavigator
import com.example.dependencyinjection.screens.common.viewsMvc.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val activity get() = activityCompositionRoot.activity
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val fragmentManager get() = activityCompositionRoot.fragmentManager

    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)
}