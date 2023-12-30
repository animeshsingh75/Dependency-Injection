package com.example.dependencyinjection.questions

import com.google.gson.annotations.SerializedName

data class QuestionWithBody(
    @SerializedName("title") val title: String,
    @SerializedName("question_id") val id: String,
    @SerializedName("body") val body: String,
    @SerializedName("owner") val owner: User,
) {
    data class User(
        @SerializedName("display_name") val name: String,
        @SerializedName("profile_image") val imageUrl: String,
    )

}