package com.htnguyen.gplxapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.htnguyen.gplxapp.dao.CompetitionDao
import com.htnguyen.gplxapp.model.Competition

@Database(entities = [Competition::class], version = 1)
abstract class CompetitionDatabase : RoomDatabase() {
    abstract fun competitionDao(): CompetitionDao

    companion object {
        private var INSTANCE: CompetitionDatabase? = null
        fun getInstance(context: Context): CompetitionDatabase {
            if (INSTANCE == null) {
                synchronized(CompetitionDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CompetitionDatabase::class.java,
                "Competition"
            ).build()

    }
}