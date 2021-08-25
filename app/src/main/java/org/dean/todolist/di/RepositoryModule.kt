package org.dean.todolist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.dean.todolist.data.remote.TodoService
import org.dean.todolist.data.repository.TodoRepository
import org.dean.todolist.data.repository.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTodoRepository(
        api: TodoService
    ): TodoRepository {
        return TodoRepositoryImpl(api)
    }

}