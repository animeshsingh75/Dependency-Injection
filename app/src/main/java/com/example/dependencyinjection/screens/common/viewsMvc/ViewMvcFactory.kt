package com.example.dependencyinjection.screens.common.viewsMvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dependencyinjection.screens.common.imageloader.ImageLoader
import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsMvc
import com.example.dependencyinjection.screens.questionslist.QuestionsListViewMvc
import javax.inject.Inject
import javax.inject.Provider

class ViewMvcFactory @Inject constructor(
    private val imageLoaderProvider: Provider<ImageLoader>,
    private val layoutInflater: LayoutInflater
) {
    fun newQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvc(layoutInflater, parent)
    }

    fun newQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsMvc {
        return QuestionDetailsMvc(layoutInflater, imageLoaderProvider.get(), parent)
    }
}