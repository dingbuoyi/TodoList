package org.dean.todolist.data.entity

import com.google.gson.annotations.SerializedName

data class TodoInfo(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)