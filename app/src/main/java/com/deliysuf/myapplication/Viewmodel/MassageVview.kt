package com.deliysuf.myapplication.Viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.deliysuf.myapplication.model.massageModel
import com.deliysuf.myapplication.model.user
import com.deliysuf.myapplication.services.nameDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MassageVview(application: Application):AndroidViewModel(application) {
     lateinit var User:user
     lateinit var UserId:String
     private lateinit var sender:String
     private lateinit var receiver:String
    private lateinit var Massages:massageModel
    val massageList=MutableLiveData<List<massageModel>>()
    val aaa=MutableLiveData<String>()
    val listOfMassages=ArrayList<massageModel>()
    private val mAuth=FirebaseAuth.getInstance()
    private val dataReferance=Firebase.database.reference
    fun getOwnerOfMassage(name:String){


            val job=viewModelScope.launch(Dispatchers.IO) {
            val db= Room.databaseBuilder(getApplication(),
                nameDataBase::class.java,"DataName").build()
            val nameMassageDao=db.name()
            User=nameMassageDao.takeUser(name)
                UserId=User.UserId.toString()


        }





    }
     fun sendMassage(massages:String){
         sender=mAuth.currentUser!!.uid.toString()+User.UserId.toString()
         receiver=User.UserId.toString()+mAuth.currentUser!!.uid
        Massages=massageModel(User.name.toString(),massages,
            mAuth.currentUser!!.uid)
        dataReferance.
        child("Massages").
        child(sender).child("massages").push().
        setValue(Massages).addOnSuccessListener {
            dataReferance.
            child("Massages").
            child(receiver).child("massages").push().
            setValue(Massages).addOnFailureListener {
                Toast.makeText(getApplication(),it.
                localizedMessage,Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(getApplication(),it.
            localizedMessage,Toast.LENGTH_LONG).show()
        }
         getMassages()


    }
     fun getMassages(){
         sender=mAuth.currentUser!!.uid.toString()+User.UserId.toString()
        dataReferance.child("Massages").
        child(sender).
        child("massages").
        addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfMassages.clear()
                for (dataSnapshot in snapshot.children){
                    Massages= massageModel(dataSnapshot.child("userName").value.toString(),
                        dataSnapshot.child("userMassages").value.toString(),
                        dataSnapshot.child("uid").value.toString())

                    listOfMassages.add(Massages)
                }
                massageList.postValue(listOfMassages)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(),
                    error.details,
                    Toast.LENGTH_LONG).
                show()
            }
        })



    }
}