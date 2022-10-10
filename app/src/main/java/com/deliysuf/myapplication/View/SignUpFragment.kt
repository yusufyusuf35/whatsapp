package com.deliysuf.myapplication.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.PopUpToBuilder
import androidx.navigation.fragment.findNavController
import com.deliysuf.myapplication.R
import com.deliysuf.myapplication.Viewmodel.SignUpVview
import com.deliysuf.myapplication.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var mail:String
    private lateinit var password:String
    private lateinit var name:String
    private lateinit var surname:String
    private lateinit var phone:String

    private val viewmodel:SignUpVview by activityViewModels()

    private var signUpBinding:FragmentSignUpBinding?=null
    private lateinit var binding: FragmentSignUpBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentSignUpBinding.bind(view)
        signUpBinding=binding
        gotoSignUp()
        observer()


        }

    fun gotoSignUp(){

        binding.signUpSigUpFrag.setOnClickListener {
            takeParameter()
            viewmodel.SignUpBaby(mail,password,name,surname,phone)

        }
    }

    fun takeParameter(){
        mail= binding.emailTextSignUp.text.trim().toString()
        password=binding.passwordTextSignUp.text.trim().toString()
        name=binding.NameText.text.trim().toString()
        surname=binding.surnameText.text.trim().toString()
        phone=binding.PhoneText.text.trim().toString()

    }
    fun observer(){
        viewmodel.result.observe(viewLifecycleOwner, Observer {
            if(it) {
                val action=SignUpFragmentDirections.
                actionSignUpFragmentToBlankFragment()
               findNavController().navigate(action)
                viewmodel.result.value=false

            }else{
                Toast.makeText(requireContext(),
                    "Error ",Toast.LENGTH_LONG).
                show()

            } })
    }


}




