package org.dean.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.dean.todolist.data.remote.doFailure
import org.dean.todolist.data.remote.doSuccess
import org.dean.todolist.data.repository.TodoRepository
import org.dean.todolist.model.TodoModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _todos = MutableLiveData<List<TodoModel>>()

    val todos: LiveData<List<TodoModel>> = _todos

    private val _failure = MutableLiveData<String>()
    val failure = _failure

    fun fetchTodoInfo(userId: Int) = liveData<List<TodoModel>> {
        todoRepository.fetchTodos(userId)
            .collectLatest { result ->
                result.doFailure { throwable ->
                    _failure.value = throwable?.message ?: "failure"
                }
                result.doSuccess { value ->
                    _todos.postValue(value)
                    emit(value)
                }
            }
    }
}