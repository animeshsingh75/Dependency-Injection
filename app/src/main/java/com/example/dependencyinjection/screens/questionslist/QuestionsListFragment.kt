package com.example.dependencyinjection.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dependencyinjection.common.dependencyInjection.Service
import com.example.dependencyinjection.questions.FetchQuestionsUseCase
import com.example.dependencyinjection.questions.Question
import com.example.dependencyinjection.screens.common.ScreensNavigator
import com.example.dependencyinjection.screens.common.activities.BaseFragment
import com.example.dependencyinjection.screens.common.dialogs.DialogsNavigator
import com.example.dependencyinjection.screens.common.viewsMvc.ViewMvcFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsListFragment : BaseFragment(), QuestionsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @field:Service
    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    @field:Service
    private lateinit var dialogsNavigator: DialogsNavigator
    @field:Service
    private lateinit var screensNavigator: ScreensNavigator
    @field:Service
    private lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var questionsListViewMvc: QuestionsListViewMvc

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        questionsListViewMvc = viewMvcFactory.newQuestionsListViewMvc(container)
        return questionsListViewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        questionsListViewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        questionsListViewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            questionsListViewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                    is FetchQuestionsUseCase.Result.Success -> {
                        questionsListViewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }

                    is FetchQuestionsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questionsListViewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }


    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(clickedQuestion: Question) {
        screensNavigator.toQuestionDetails(clickedQuestion.id)
    }
}