package com.htnguyen.gplxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.TrafficsLearn

@Database(entities = [TrafficsLearn::class], version = 1)
abstract class TrafficLearnDatabase : RoomDatabase() {
    abstract fun trafficLearnDao(): TrafficLearnDao

    companion object {
        private var INSTANCE: TrafficLearnDatabase? = null
        fun getInstance(context: Context): TrafficLearnDatabase {
            if (INSTANCE == null) {
                synchronized(TrafficLearnDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TrafficLearnDatabase::class.java,
                "TrafficsLearn"
            ).build()

    }
}