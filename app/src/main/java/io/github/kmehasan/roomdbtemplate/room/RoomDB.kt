package io.github.kmehasan.roomdbtemplate.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class,Course::class,Mark::class], version = 1)
abstract class RoomDB() : RoomDatabase() {
    companion object {
        var db:RoomDB? = null
        fun getInstance(context: Context):RoomDB{
            return if(db!=null) db as RoomDB
            else {
                db = Room.databaseBuilder(context,RoomDB::class.java,"Course_management").build()
                db as RoomDB
            }
        }
    }

    abstract fun getDAO():DAO

}