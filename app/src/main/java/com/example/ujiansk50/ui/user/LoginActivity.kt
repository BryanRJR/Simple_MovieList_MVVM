package com.example.ujiansk50.ui.user

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ujiansk50.MainActivity
import com.example.ujiansk50.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etName = binding.insUsername
        val etPassword = binding.insPassword

        binding.btnLogin.setOnClickListener {
            val user: String = etName.text.toString()
            val password: String = etPassword.text.toString()
            val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
            val userDetails = preferences.getString("user", "defaultname")
            val passDetails = preferences.getString("pass", "defaultname")

            if (user != userDetails && password != passDetails){
                Toast.makeText(this, "Username Or Password Invalid", Toast.LENGTH_SHORT).show()
            } else {
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("display", userDetails)
                editor.commit()
                val displayScreen = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(displayScreen)
            }
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    // set hide action bar onStart
    override fun onStart() {
        super.onStart()
        (this as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
    // set hide action bar onStop
    override fun onStop() {
        super.onStop()
        (this as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}