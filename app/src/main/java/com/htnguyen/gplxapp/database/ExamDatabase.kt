package com.htnguyen.gplxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.htnguyen.gplxapp.dao.ExamDao
import com.htnguyen.gplxapp.model.Exam

@Database(entities = [Exam::class], version = 1)
abstract class ExamDatabase : RoomDatabase() {
    abstract fun examDao(): ExamDao

    companion object {
        private var INSTANCE: ExamDatabase? = null
        fun getInstance(context: Context): ExamDatabase {
            if (INSTANCE == null) {
                synchronized(ExamDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ExamDatabase::class.java,
                "Exam"
            ).build()

    }
}