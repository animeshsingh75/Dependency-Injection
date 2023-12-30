package com.example.dependencyinjection.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.dependencyinjection.common.dependencyInjection.presentation.PresentationModule
import com.example.dependencyinjection.screens.common.activities.BaseActivity

open class BaseDialog : DialogFragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule(this)
        )
    }

    protected val injector get() = presentationComponent
}