package com.example.dependencyinjection.networking

import com.google.gson.annotations.SerializedName
import com.example.dependencyinjection.questions.Question

data class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)