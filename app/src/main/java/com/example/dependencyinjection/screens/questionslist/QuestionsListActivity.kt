package com.example.dependencyinjection.screens.questionslist

import android.os.Bundle
import com.example.dependencyinjection.R
import com.example.dependencyinjection.screens.common.ScreensNavigatorImpl
import com.example.dependencyinjection.screens.common.activities.BaseActivity
import com.example.dependencyinjection.screens.common.viewsMvc.ScreensNavigator
import javax.inject.Inject

class QuestionsListActivity : BaseActivity() {

    @Inject
    lateinit var screensNavigator: ScreensNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, QuestionsListFragment())
                .commit()
        }
    }
}