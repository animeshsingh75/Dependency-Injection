package com.example.dependencyinjection.screens.common.activities

import androidx.fragment.app.Fragment
import com.example.dependencyinjection.common.dependencyInjection.presentation.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule(this)
        )
    }

    protected val injector get() = presentationComponent
}