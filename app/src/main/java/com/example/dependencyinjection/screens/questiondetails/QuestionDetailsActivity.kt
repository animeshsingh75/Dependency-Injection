package com.example.dependencyinjection.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.dependencyinjection.questions.FetchQuestionDetailsUseCase
import com.example.dependencyinjection.screens.common.ScreensNavigatorImpl
import com.example.dependencyinjection.screens.common.activities.BaseActivity
import com.example.dependencyinjection.screens.common.dialogs.DialogsNavigator
import com.example.dependencyinjection.screens.common.viewsMvc.ScreensNavigator
import com.example.dependencyinjection.screens.common.viewsMvc.ViewMvcFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject
    lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var questionDetailsMvc: QuestionDetailsMvc

    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        questionDetailsMvc = viewMvcFactory.newQuestionDetailsViewMvc(null)
        setContentView(questionDetailsMvc.rootView)


        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        questionDetailsMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        questionDetailsMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            questionDetailsMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionDetailsUseCase.fetchLatestQuestions(questionId)) {
                    is FetchQuestionDetailsUseCase.Result.Success -> {
                        questionDetailsMvc.bindQuestionBody(result.question)
                    }

                    is FetchQuestionDetailsUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questionDetailsMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }


    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        screensNavigator.navigateBack()
    }
}