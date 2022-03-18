package com.example.cadastroteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cadastroteste.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        auth = Firebase.auth
        startComponent()
        getCurrentUser()


    }

    private fun createAccount(email:String, password:String ){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Sucesso!","CreateUserWithEmail Success")
                    val user = auth.currentUser
                    Toast.makeText(this,"Cadastro certo",Toast.LENGTH_LONG).show()
                }else{
                    Log.w("falha","Falha para createUserWithEmail")
                    Toast.makeText(this,"Falha no cadastro",Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun getCurrentUser(){
        val user = Firebase.auth.currentUser
        user?.let {
            val nome = user.displayName
            val email = user.email
            val emailVerified = user.isEmailVerified
            val userId = user.uid
            binding.button2.setOnClickListener {
                 intent = Intent(this,UserInformationActivity2::class.java)
                intent.putExtra("nome",nome.toString())
                intent.putExtra("email",email.toString())
                intent.putExtra("userID",userId.toString())
                startActivity(intent)
            }
        }

    }

    private fun startComponent(){
        val nome = binding.itNome.text
        val email = binding.itEmail.text
        val senha = binding.itSenha.text
        binding.button.setOnClickListener {
            createAccount(email.toString(),senha.toString())
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this,"Tudo certo!",Toast.LENGTH_LONG).show()
        }
    }
}