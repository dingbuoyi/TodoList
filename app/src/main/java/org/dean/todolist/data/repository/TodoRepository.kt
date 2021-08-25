package org.dean.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import org.dean.todolist.data.remote.TodoResult
import org.dean.todolist.model.TodoModel

interface TodoRepository {

    suspend fun fetchTodos(userId: Int): Flow<TodoResult<List<TodoModel>>>

}