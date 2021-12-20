package com.lazymindapps.naxamobile.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lazymindapps.naxamobile.MainActivity
import com.lazymindapps.naxamobile.R
import com.lazymindapps.naxamobile.databinding.FragmentAboutBinding
import com.lazymindapps.naxamobile.sharedPref.SharedPrefClass

class AboutFragment : Fragment() {
    lateinit var binding :FragmentAboutBinding
    lateinit var sharedPrefClass:SharedPrefClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAboutBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefClass = context?.let { SharedPrefClass(it) }!!

        binding.btnLogout.setOnClickListener {
            sharedPrefClass?.saveToken("0")
          startActivity(Intent(activity,MainActivity::class.java))
            activity?.onBackPressed()

        }
    }

}