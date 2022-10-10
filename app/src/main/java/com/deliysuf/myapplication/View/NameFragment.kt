package com.deliysuf.myapplication.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.Viewmodel.NameVview
import com.deliysuf.myapplication.adapter.nameAdapter
import com.deliysuf.myapplication.databinding.FragmentNameBinding
import com.deliysuf.myapplication.model.user


class NameFragment : Fragment() {
  private var NameFragmentBinding:FragmentNameBinding?=null
    private lateinit var binding:FragmentNameBinding
    private lateinit var nameAdapter: nameAdapter
    private val viewModelName:NameVview by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentNameBinding.bind(view)
        NameFragmentBinding=binding
        binding.nameRecyclerView.
        layoutManager= LinearLayoutManager(requireContext())
        viewModelName.fetchUser()
        fetchFromAddFrag()


        addUser()


    }
   fun addUser(){
       binding.AddUserButton.setOnClickListener{
       val action=NameFragmentDirections.actionNameFragmentToAddFragment()
       findNavController().navigate(action)
   }}

    fun fetchFromAddFrag(){
        viewModelName.userList.observe(viewLifecycleOwner, Observer {
            nameAdapter= nameAdapter(it as ArrayList<user>)
            binding.nameRecyclerView.adapter=nameAdapter
        })
    }


}