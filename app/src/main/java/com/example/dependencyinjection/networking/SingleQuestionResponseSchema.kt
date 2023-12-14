package com.example.dependencyinjection.networking

import com.google.gson.annotations.SerializedName
import com.example.dependencyinjection.questions.QuestionWithBody

data class SingleQuestionResponseSchema(@SerializedName("items") val questions: List<QuestionWithBody>) {
    val question: QuestionWithBody get() = questions[0]
}