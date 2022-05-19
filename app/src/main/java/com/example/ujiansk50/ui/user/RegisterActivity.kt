package com.example.ujiansk50.ui.user

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ujiansk50.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // call function btnSubmit
        btnSubmit()

        // btn go back to login screen
        binding.btnGoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    // set form id to pass data and use condition to handle empty input
    private fun btnSubmit() {
        val userName = binding.edtDiaEmailId.text
        val password = binding.edtDiaPassword.text
        val email = binding.edtDiaEmailId.text
        val conPass = binding.putConfirmPass.text

        binding.btnDiaSubmit.setOnClickListener {

            if ( userName!!.isEmpty() && password!!.isEmpty() && email!!.isEmpty() && conPass!!.isEmpty() ) {
                Toast.makeText(this, "Fill The Register Form", Toast.LENGTH_SHORT).show()
            } else {
                val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
                val newUser: String = userName.toString()
                val newPassword: String = password.toString()
                val newEmail: String = email.toString()
                val editor: SharedPreferences.Editor = preferences.edit()

                editor.putString("user", newUser)
                editor.putString("pass", newPassword)
                editor.putString("email", newEmail)
                editor.commit()

                Toast.makeText(this, "Account Have Been Created", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }
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