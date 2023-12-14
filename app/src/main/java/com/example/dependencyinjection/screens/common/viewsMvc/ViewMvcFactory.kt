package com.example.dependencyinjection.screens.common.viewsMvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsMvc
import com.example.dependencyinjection.screens.questionslist.QuestionsListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvc(layoutInflater, parent)
    }

    fun newQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsMvc {
        return QuestionDetailsMvc(layoutInflater, parent)
    }
}