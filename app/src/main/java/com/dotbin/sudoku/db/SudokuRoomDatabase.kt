package com.dotbin.sudoku.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SudokuGame::class], version = 1)
abstract class SudokuRoomDatabase : RoomDatabase() {
    abstract fun SudokuDao(): SudokuDao

    companion object {
        @Volatile
        private var INSTANCE: SudokuRoomDatabase? = null

        fun getDatabase(context: Context): SudokuRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SudokuRoomDatabase::class.java,
                    "sudoku_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}