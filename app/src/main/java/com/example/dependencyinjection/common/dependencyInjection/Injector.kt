package com.example.dependencyinjection.common.dependencyInjection

import com.example.dependencyinjection.questions.FetchQuestionDetailsUseCase
import com.example.dependencyinjection.questions.FetchQuestionsUseCase
import com.example.dependencyinjection.screens.common.ScreensNavigator
import com.example.dependencyinjection.screens.common.dialogs.DialogsNavigator
import com.example.dependencyinjection.screens.common.viewsMvc.ViewMvcFactory
import java.lang.reflect.Field

class Injector(private val presentationCompositionRoot: PresentationCompositionRoot) {
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForFields(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForFields(field: Field): Boolean {
        val fieldAnnotation = field.annotations
        for (annotation in fieldAnnotation) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }


    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForField(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForField(type: Class<*>?): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return presentationCompositionRoot.dialogsNavigator
            }

            ScreensNavigator::class.java -> {
                return presentationCompositionRoot.screensNavigator
            }

            FetchQuestionDetailsUseCase::class.java -> {
                return presentationCompositionRoot.fetchQuestionDetailsUseCase
            }

            FetchQuestionsUseCase::class.java -> {
                return presentationCompositionRoot.fetchQuestionsUseCase
            }

            ViewMvcFactory::class.java -> {
                return presentationCompositionRoot.viewMvcFactory
            }

            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}