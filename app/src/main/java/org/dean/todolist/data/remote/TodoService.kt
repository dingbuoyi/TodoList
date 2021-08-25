package org.dean.todolist.data.remote

import org.dean.todolist.data.entity.TodoInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoService {

    @GET("todos")
    suspend fun fetchTodos(@Query("userId") userId: Int): List<TodoInfo>
}