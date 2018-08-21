package com.example.rahulajmera.well

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.rahulajmera.well.ViewModels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = LoginViewModel()
        logInObserver()
        setContentView(R.layout.activity_login)
    }


    override fun onStart() {
        super.onStart()
        login_button.setOnClickListener { view ->
            loginViewModel.login(username_text_input.text.toString(), password_text_input.text.toString())
        }
    }

    fun logInObserver() {
        loginViewModel.firebaseLoginObservable.subscribe {e ->
            Log.d("HERE", e.toString())
            if (e) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}