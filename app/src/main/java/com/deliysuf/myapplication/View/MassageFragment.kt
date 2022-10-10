package com.deliysuf.myapplication.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.Viewmodel.MassageVview
import com.deliysuf.myapplication.adapter.MassageAdapter
import com.deliysuf.myapplication.databinding.FragmentMassageBinding
import com.deliysuf.myapplication.model.massageModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking


class MassageFragment : Fragment() {
    private val mAuth=FirebaseAuth.getInstance()
    private lateinit var UserId:String
private lateinit var userName:String
lateinit var Massage:String
private lateinit var adapterOfMassages:MassageAdapter
private lateinit var massageFragment: FragmentMassageBinding
private lateinit var binding: FragmentMassageBinding
private val massageModel:MassageVview by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       userName= arguments?.getString("name").toString()

            massageModel.getOwnerOfMassage(userName)





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_massage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding= FragmentMassageBinding.bind(view)
        val Manager= LinearLayoutManager(requireContext())
        Manager.stackFromEnd=true
        binding.MassageRecyclerView.
        layoutManager=Manager


        massageFragment=binding
        acceptMassages()
        sendingButton(binding)
        }
    fun sendingButton(binding: FragmentMassageBinding){
            binding.MassageSendButton.setOnClickListener {
            Massage=binding.textMassage.text.toString()
            massageModel.sendMassage(Massage)

        }
    }
    fun acceptMassages(){
        massageModel.massageList.observe(viewLifecycleOwner, Observer {
            adapterOfMassages= MassageAdapter(it as ArrayList<massageModel>,mAuth.currentUser!!.uid,massageModel.UserId)
            binding.MassageRecyclerView.adapter=adapterOfMassages
            binding.MassageRecyclerView.smoothScrollToPosition(adapterOfMassages.itemCount-1)
            adapterOfMassages.notifyDataSetChanged()

        })
    }
    }

