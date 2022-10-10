package com.deliysuf.myapplication.Viewmodel

import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.deliysuf.myapplication.model.user
import com.deliysuf.myapplication.services.nameDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NameVview(application: Application):AndroidViewModel(application) {
    private val mAuth=FirebaseAuth.getInstance()
    lateinit var User:user

    var userList=MutableLiveData<List<user>>()
    var list=ArrayList<user>()
    var dizi= mutableSetOf<user>()

    private var fireDatabase=Firebase.database.reference

    fun fetchUser(){

        fireDatabase.child("Users").
        child(mAuth.currentUser!!.uid).
        child("contacts").
        get().addOnSuccessListener {
          runBlocking {
              viewModelScope.launch(Dispatchers.Default) {

                  for(snapshot in it.children){
                      User= user(0,
                          snapshot.child("email").value.toString(),
                          snapshot.child("password").value.toString(),
                          snapshot.child("name").value.toString(),
                          snapshot.child("surname").value.toString(),
                          snapshot.child("Phone").value.toString(),
                          snapshot.key.toString())
                      dizi.add(User)
                  }
              }
              recordUser()
          }
        }.addOnFailureListener{
            Toast.makeText(getApplication(),
                "Error",Toast.LENGTH_LONG).show()
        }

    }
     suspend fun recordUser()=viewModelScope.launch(Dispatchers.IO){


         val db=Room.databaseBuilder(getApplication(),nameDataBase::class.java,"DataName").build()
             val dataDao=db.name()
             dataDao.allDelete()
             dataDao.insertAll(dizi)
             userList.postValue(dataDao.getAll())
         }





}