package com.example.roomwordsample.di

import android.content.Context
import com.example.roomwordsample.data.WordDao
import com.example.roomwordsample.data.WordRoomDatabase
import com.example.roomwordsample.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repository {

    @Provides
    @Singleton
    fun provideWordRepository(database: WordRoomDatabase): WordRepository {
        return WordRepository(wordDao = database.wordDao())
    }

    @Provides
    @Singleton
    fun provideWordRoomDatabase(@ApplicationContext context: Context): WordRoomDatabase {
        return WordRoomDatabase.getDatabase(context)
    }

}