package com.example.dependencyinjection.questions

import com.example.dependencyinjection.networking.StackoverflowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FetchQuestionDetailsUseCase @Inject constructor(private val stackoverflowApi: StackoverflowApi) {
    sealed class Result {
        data class Success(val question: QuestionWithBody) : Result()
        object Failure : Result()
    }

    suspend fun fetchLatestQuestions(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question
                    return@withContext Result.Success(questionBody)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}