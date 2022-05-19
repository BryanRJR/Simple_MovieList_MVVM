package com.example.ujiansk50.ui.user

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ujiansk50.R
import com.example.ujiansk50.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // call fun btnUpdate
        btnUpdate()

    }

    // create fun to update data when click btn update
    private fun btnUpdate() {
        // load and set sharedpreferences
        val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val savedUser = preferences.getString("user", "defaultname")
        val savedEmail = preferences.getString("email", "defaultname")
        val savedPass = preferences.getString("pass", "defaultname")

        binding.edtUsername.setText(savedUser)
        binding.edtEmail.setText(savedEmail)
        binding.edtPassword.setText(savedPass)

        val userName = binding.edtUsername.text
        val password = binding.edtPassword.text
        val email = binding.edtEmail.text
        val conPass = binding.edtConfirmPass.text

        binding.btnUpdate.setOnClickListener {

            if ( userName!!.isEmpty() && password!!.isEmpty() && email!!.isEmpty() && conPass!!.isEmpty() ) {
                Toast.makeText(this, getString(R.string.act_profile_toast_form), Toast.LENGTH_SHORT).show()
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

                Toast.makeText(this, getString(R.string.act_profile_toast), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
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