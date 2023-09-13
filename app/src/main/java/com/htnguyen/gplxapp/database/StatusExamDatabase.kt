package com.htnguyen.gplxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.htnguyen.gplxapp.dao.StatusExamDao
import com.htnguyen.gplxapp.dao.StatusLearnDao
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.StatusExam
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.StatusLearnConverter
import com.htnguyen.gplxapp.model.TrafficsLearn

@Database(entities = [StatusExam::class], version = 1)
abstract class StatusExamDatabase : RoomDatabase() {
    abstract fun statusExamDao(): StatusExamDao

    companion object {
        private var INSTANCE: StatusExamDatabase? = null
        fun getInstance(context: Context): StatusExamDatabase {
            if (INSTANCE == null) {
                synchronized(StatusExamDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StatusExamDatabase::class.java,
                "StatusExam"
            ).build()

    }
}