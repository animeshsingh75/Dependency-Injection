package com.example.dependencyinjection.screens.questionslist

import android.os.Bundle
import com.example.dependencyinjection.R
import com.example.dependencyinjection.screens.common.activities.BaseActivity

class QuestionsListActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, QuestionsListFragment())
                .commit()
        }
    }
}