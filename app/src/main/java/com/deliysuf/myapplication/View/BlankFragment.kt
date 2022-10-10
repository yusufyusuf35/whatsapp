package com.deliysuf.myapplication.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.Viewmodel.BlankVview
import com.deliysuf.myapplication.databinding.FragmentBlankBinding




class BlankFragment : Fragment() {
    private var blankBinding:FragmentBlankBinding?=null
    private lateinit var binding:FragmentBlankBinding
    private lateinit var mail:String
    private lateinit var password:String
    private val viewModel:BlankVview by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)
        blankBinding = binding
        goToSignup()
        goToActivity()
        observers()

    }

    fun goToSignup(){
       binding.signUpButtonEnter.setOnClickListener {
            val action=BlankFragmentDirections.
            actionBlankFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }

        }
    fun goToActivity(){
        binding.sigInButtonEnter.setOnClickListener {
            takeParameters()
            viewModel.SignInBaby(mail,password)
        }
    }
    fun takeParameters(){
        mail=binding.signIpEmailText.text.trim().toString()
        password=binding.signInPasswordText.text.trim().toString()
    }

    fun observers(){
        viewModel.result.observe(viewLifecycleOwner, Observer {
            if(it){
                val intent=Intent(requireContext(),MainActivity2::class.java)
                startActivity(intent)
                viewModel.result.value=false
            }
            else{
                Toast.makeText(requireContext(),
                    "Error",Toast.LENGTH_LONG).show()
            }
        })
    }

}