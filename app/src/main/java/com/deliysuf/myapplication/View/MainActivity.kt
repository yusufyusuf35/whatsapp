package com.deliysuf.myapplication.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deliysuf.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
    fun start():Boolean{
        var a=false
        if(mAuth.currentUser!=null){
            a=true
        }
        return a
    }
}