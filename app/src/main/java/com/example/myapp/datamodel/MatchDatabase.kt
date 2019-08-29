package com.example.myapp.datamodel

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapp.model.Venues
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Venues::class],exportSchema = false,version = 3)
abstract class MatchDatabase : RoomDatabase() {

    abstract fun matchdao():MatchDao
    companion object{

        @Volatile
        var INSTANCE : MatchDatabase? = null
        fun getInstance(context: Context):MatchDatabase{

            return INSTANCE?:synchronized(this){
                var instance = Room.databaseBuilder(context.applicationContext,MatchDatabase::class.java,"placedb")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
        }
    }

    class MatchCallBack(private var scope : CoroutineScope) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

        }
    }


}