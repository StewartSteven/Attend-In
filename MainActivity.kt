package com.example.tutorialpart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        studentButton.setOnClickListener{
            startActivity(Intent(this, StudentPath::class.java))

        teacherButton.setOnClickListener{
            startActivity(Intent(this, TeacherPath::class.java))
        }


    }
}
}