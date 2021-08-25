package org.dean.todolist.model

import org.dean.todolist.data.entity.TodoInfo

data class TodoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    var completed: Boolean
) {

    companion object {
        fun convert2Model(info: TodoInfo): TodoModel {
            return TodoModel(
                userId = info.userId,
                id = info.id,
                title = info.title,
                completed = info.completed
            )
        }

        fun userInputModel(title: String): TodoModel {
            return TodoModel(
                userId = 0,
                id = 0,
                title = title,
                completed = false
            )
        }
    }
}