package com.deliysuf.myapplication.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class BlankVview():ViewModel() {
  private var mAuth=FirebaseAuth.getInstance()
    var result=MutableLiveData<Boolean>()
     fun SignInBaby(mail:String,password:String){

         if(mail.isNotEmpty()&&password.isNotEmpty()){
         mAuth.signInWithEmailAndPassword(mail,password).
         addOnSuccessListener {
             result.value=true
         }.addOnFailureListener{
             result.value=false
         }}
     }



}