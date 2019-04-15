package com.example.jetpackapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackapp.R
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
