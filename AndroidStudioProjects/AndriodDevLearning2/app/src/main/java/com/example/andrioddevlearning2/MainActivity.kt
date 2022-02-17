package com.example.andrioddevlearning2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBtnClick (view : View) {
        var firstNameView: TextView  = findViewById(R.id.firstNameView)
        var lastNameView: TextView  = findViewById(R.id.lastNameView)
        var emailView: TextView  = findViewById(R.id.emailView)
        var firstName: EditText = findViewById(R.id.firstName)
        var lastName: EditText = findViewById(R.id.lastName)
        var email: EditText = findViewById(R.id.email)

        Toast.makeText(this, "Happy as can be", Toast.LENGTH_SHORT).show()

        firstNameView.text = (firstName.text.toString())
        lastNameView.text = (lastName.text.toString())
        emailView.text = (email.text.toString())

        //var edtTxtName: TextView = findViewById(R.id.txtHelloView)
    }
}