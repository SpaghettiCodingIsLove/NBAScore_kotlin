package com.example.nbascore.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbascore.Model.Entities.FavoriteTeam
import com.example.nbascore.Model.Entities.FavoriteTeamDao

@Database(entities = [FavoriteTeam::class], version = 7, exportSchema = false)
abstract class NBADatabase: RoomDatabase(){
    abstract fun favoriteTeamDao(): FavoriteTeamDao

    companion object{
        @Volatile
        private var INSTANCE: NBADatabase? = null

        fun getDatabase(context: Context): NBADatabase{
            var tempInstance = INSTANCE

            if(tempInstance != null)
            {
                return tempInstance
            }
            else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NBADatabase::class.java,
                        "myDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}