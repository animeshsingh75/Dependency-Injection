package com.example.dependencyinjection.screens.questiondetails

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dependencyinjection.R
import com.example.dependencyinjection.questions.QuestionWithBody
import com.example.dependencyinjection.screens.common.imageloader.ImageLoader
import com.example.dependencyinjection.screens.common.toolbar.MyToolbar
import com.example.dependencyinjection.screens.common.viewsMvc.BaseViewMvc

class QuestionDetailsMvc(
    layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?
) : BaseViewMvc<QuestionDetailsMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_question_details
) {
    interface Listener {
        fun onBackClicked()
    }

    private var toolbar: MyToolbar
    private var swipeRefresh: SwipeRefreshLayout
    private var txtQuestionBody: TextView
    private var txtUserName: TextView
    private var imgUser: ImageView


    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)
        txtUserName = findViewById(R.id.txt_user_name)
        imgUser = findViewById(R.id.image_user)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackClicked()
            }
        }
        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }

    fun bindQuestionBody(questionBody: QuestionWithBody) {
        txtQuestionBody.text = Html.fromHtml(questionBody.body, Html.FROM_HTML_MODE_LEGACY)
        txtUserName.text = questionBody.owner.name
        imageLoader.loadImage(questionBody.owner.imageUrl, imgUser)

    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }
}
