package org.dean.todolist.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.dean.todolist.data.remote.TodoResult
import org.dean.todolist.data.remote.TodoService
import org.dean.todolist.model.TodoModel

class TodoRepositoryImpl (
    private val api: TodoService,
) : TodoRepository {

     override suspend fun fetchTodos(userId: Int): Flow<TodoResult<List<TodoModel>>> {
        return flow {
            try {
                val todoInfoList = api.fetchTodos(userId)
                val todoModelList = mutableListOf<TodoModel>()
                todoInfoList.forEach {
                    todoModelList.add(TodoModel.convert2Model(it))
                }
                emit(TodoResult.Success(todoModelList))
            } catch (e: Exception) {
                emit(TodoResult.Failure(e.cause))
            }
        }.flowOn(Dispatchers.IO)
    }

}