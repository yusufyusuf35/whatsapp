package com.deliysuf.myapplication.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deliysuf.myapplication.model.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpVview:ViewModel() {
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var result = MutableLiveData<Boolean>()
    var fireDatabase = Firebase.database.reference
    lateinit var User: user
    fun SignUpBaby(mail: String, Password: String, name: String, surname: String, Phone: String) {

        mAuth.createUserWithEmailAndPassword(mail, Password).addOnSuccessListener {
            User = user(0, mail, Password, name, surname, Phone, mAuth.currentUser!!.uid)
            fireDatabase.child("Users").child(it.user!!.uid).setValue(User)
            result.value = true
        }.addOnFailureListener {
            result.value = false
        }

    }





    }

