package com.example.dependencyinjection.common.dependencyInjection.presentation

import com.example.dependencyinjection.screens.questiondetails.QuestionDetailsActivity
import com.example.dependencyinjection.screens.questionslist.QuestionsListActivity
import com.example.dependencyinjection.screens.questionslist.QuestionsListFragment
import com.example.dependencyinjection.screens.viewmodel.ViewModelActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(fragment: QuestionsListFragment)
    fun inject(activity: QuestionDetailsActivity)
    fun inject(activity: QuestionsListActivity)
    fun inject(activity: ViewModelActivity)
}