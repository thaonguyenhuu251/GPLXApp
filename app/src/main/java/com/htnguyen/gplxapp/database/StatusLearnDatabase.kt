package com.htnguyen.gplxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.htnguyen.gplxapp.dao.StatusLearnDao
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.StatusLearnConverter
import com.htnguyen.gplxapp.model.TrafficsLearn

@Database(entities = [StatusLearn::class], version = 1)
abstract class StatusLearnDatabase : RoomDatabase() {
    abstract fun statusLearnDao(): StatusLearnDao

    companion object {
        private var INSTANCE: StatusLearnDatabase? = null
        fun getInstance(context: Context): StatusLearnDatabase {
            if (INSTANCE == null) {
                synchronized(StatusLearnDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StatusLearnDatabase::class.java,
                "StatusLearn"
            ).build()

    }
}