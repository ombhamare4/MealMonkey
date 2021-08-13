package com.rubiks.mealmonkey.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rubiks.mealmonkey.R

class LoginActivity : AppCompatActivity() {

    lateinit var etYourEamil: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnFacbookLogin: Button
    lateinit var btnGoogleLogin: Button
    lateinit var txtSigUp: TextView
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        etYourEamil = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnlogin)
        btnFacbookLogin = findViewById(R.id.btnfacbooklogin)
        btnGoogleLogin = findViewById(R.id.btngooglelogin)
        txtSigUp = findViewById(R.id.txtSignUp)

        //Firebase Setup
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        btnLogin.setOnClickListener {
            validateUser()
        }

        txtSigUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }

    fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    fun validateUser() {
        val email = etYourEamil.text.toString()
        val password = etPassword.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etYourEamil.error = "Invalid Email"
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Please Enter Password"
        } else {
            firebaseLogin()
        }
    }

    fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(
            etYourEamil.text.toString(),
            etPassword.text.toString()
        ).addOnSuccessListener {
            val firebaseUser = firebaseAuth.currentUser
            val email = firebaseUser?.email
            Toast.makeText(this@LoginActivity, "User Found", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()

        }.addOnFailureListener { e ->
            Toast.makeText(this@LoginActivity, "User Not Found", Toast.LENGTH_SHORT).show()
        }
    }
}