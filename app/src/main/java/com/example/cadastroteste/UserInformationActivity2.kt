package com.example.cadastroteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.cadastroteste.databinding.ActivityUserInformation2Binding



class UserInformationActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityUserInformation2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInformation2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val dados : Bundle? = intent.extras
        val email : String? = dados?.getString("email")
        val id : String? = dados?.getString("userID")

        binding.tvInformation.text = email + " "+id

    }
}