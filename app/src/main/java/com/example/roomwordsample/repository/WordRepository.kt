package com.example.roomwordsample.repository

import com.example.roomwordsample.data.Word
import com.example.roomwordsample.data.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

}