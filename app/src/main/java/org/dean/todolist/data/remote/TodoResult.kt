package org.dean.todolist.data.remote

sealed class TodoResult<out T> {
    data class Success<out T>(val value: T) : TodoResult<T>()

    data class Failure(val throwable: Throwable?) : TodoResult<Nothing>()
}

inline fun <reified T> TodoResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is TodoResult.Success) {
        success(value)
    }
}

inline fun <reified T> TodoResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is TodoResult.Failure) {
        failure(throwable)
    }
}