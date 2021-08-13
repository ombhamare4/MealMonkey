package com.rubiks.mealmonkey.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.databinding.ActivityMainBinding
import java.sql.DatabaseMetaData
import java.util.*

class RegisterActivity : AppCompatActivity() {
    lateinit var etName :EditText
    lateinit var etEmail: EditText
    lateinit var etMobileNumber:EditText
    lateinit var etAddress: EditText
    lateinit var etPassword : EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnSignUp:Button
    lateinit var txtLogin : TextView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binder: ActivityMainBinding
    lateinit var database: DatabaseMetaData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        etName = findViewById(R.id.etname)
        etEmail = findViewById(R.id.etemail)
        etMobileNumber = findViewById(R.id.etmobilenumber)
        etAddress = findViewById(R.id.etaddress)
        etPassword = findViewById(R.id.etpassword)
        etConfirmPassword = findViewById(R.id.etconfirmpassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        txtLogin = findViewById(R.id.txtlogin)


        //Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()


        btnSignUp.setOnClickListener {
            validateData()

        }
        txtLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }

    }

    fun validateData(){
        val name = etName.text.toString().toUpperCase(Locale.ROOT)
        val email = etEmail.text.toString()
        val mobileNumber = etMobileNumber.text.toString()
        val address = etAddress.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Invalid Email"
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Please Enter Password"
        } else if(TextUtils.isEmpty(name)) {
            etName.error ="Please Enter Name"
        }else if(TextUtils.isEmpty(mobileNumber)) {
            etName.error ="Please Enter Mobile Number"
        }else if(TextUtils.isEmpty(address)) {
            etName.error ="Please Enter Address"
        }else if(password.length<8) {
            etName.error ="Password Should be at least 8 characters"
        }
        else if(password!=confirmPassword) {
            etName.error ="Password and Confirm Password Not Match"
        }
        else{
            firebaseSignUp()
        }
    }

    fun firebaseSignUp(){

        val name = etName.text.toString().toUpperCase(Locale.ROOT)
        val email = etEmail.text.toString()
        val mobileNumber = etMobileNumber.text.toString()
        val address = etAddress.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        firebaseAuth.createUserWithEmailAndPassword(etEmail.text.toString(),etConfirmPassword.text.toString())
    }
}