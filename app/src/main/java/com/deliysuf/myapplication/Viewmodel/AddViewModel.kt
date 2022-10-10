package com.deliysuf.myapplication.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deliysuf.myapplication.model.user
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class AddViewModel:ViewModel() {
    var UserId=""
    val mAuth= FirebaseAuth.getInstance()
    lateinit var User: user
    val result=MutableLiveData<Boolean>()
    var fireDatabase= Firebase.database.reference

    fun addUser(value:String){
        User=user(0,"s","s","s","s","d","")
        fireDatabase.child("Users").get().addOnSuccessListener {

            viewModelScope.launch(Dispatchers.Default) {
            for (snapshot in it.children){
                if(snapshot.child("phone").value==value){
                    User=user(0,
                        snapshot.child("email").value.toString(),
                              snapshot.child("password").value.toString(),
                              snapshot.child("name").value.toString(),
                              snapshot.child("surname").value.toString(),
                              snapshot.child("phone").value.toString(),
                              snapshot.key.toString())
                   UserId=snapshot.key.toString()
                    fireDatabase.
                    child("Users").
                    child(mAuth.currentUser!!.uid).
                    child("contacts").
                    child(UserId).setValue(User).addOnSuccessListener {
                        result.postValue(true)
                    }

                }
                }
            }


        }.addOnFailureListener{
            result.value=false

        }
    }



}