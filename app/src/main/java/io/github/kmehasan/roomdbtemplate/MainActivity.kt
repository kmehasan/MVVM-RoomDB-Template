package io.github.kmehasan.roomdbtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import io.github.kmehasan.roomdbtemplate.room.RoomDB

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}