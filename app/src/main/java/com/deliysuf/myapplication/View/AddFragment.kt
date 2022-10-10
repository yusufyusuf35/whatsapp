package com.deliysuf.myapplication.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.Viewmodel.AddViewModel
import com.deliysuf.myapplication.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private lateinit var Addbinding:FragmentAddBinding
    private lateinit var binding: FragmentAddBinding
    private val viewModel:AddViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAddBinding.bind(view)
        Addbinding=binding
        Adding()



    }
   fun Adding(){
       binding.AddButton.setOnClickListener {
          viewModel.addUser(binding.addingText.text.toString())
       }
       viewModel.result.observe(viewLifecycleOwner, Observer {
           if(it){
               val action=AddFragmentDirections.actionAddFragmentToNameFragment()
               findNavController().navigate(action)
               viewModel.result.value=false
           }
       })
   }

}