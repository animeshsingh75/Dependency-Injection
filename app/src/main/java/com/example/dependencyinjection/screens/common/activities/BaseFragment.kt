package com.example.dependencyinjection.screens.common.activities

import androidx.fragment.app.Fragment
import com.example.dependencyinjection.common.dependencyInjection.Injector
import com.example.dependencyinjection.common.dependencyInjection.PresentationCompositionRoot

open class BaseFragment : Fragment() {
    private val compositionRoot by lazy {
        PresentationCompositionRoot(
            (requireActivity() as BaseActivity).activityCompositionRoot
        )
    }

    protected val injector get() = Injector(compositionRoot)
}