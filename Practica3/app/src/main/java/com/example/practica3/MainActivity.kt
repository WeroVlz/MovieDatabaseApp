package com.example.practica3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.buttonIn)
        val logout = findViewById<Button>(R.id.buttonOut)


        login.setOnClickListener {
            var emailStr = email.text.toString()
            var passwordStr = password.text.toString()

            if(email.text.isEmpty()){
                emailStr = "null"
            }
            if(password.text.isEmpty()){
                passwordStr = "null"
            }

            auth.signInWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this
                ) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Authenticated user.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, Principal::class.java))
                        //finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // ...
                }
        }

        logout.setOnClickListener {
            Toast.makeText(this,"User has disconnected.", Toast.LENGTH_LONG).show()
            auth.signOut()
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        userSignedIn(currentUser)

    }

    private fun userSignedIn(user:FirebaseUser?){
        if(user == null)
            Toast.makeText(this,"No authenticated user.", Toast.LENGTH_LONG).show()
        else{
            Toast.makeText(this, "The user " + user.email.toString()+" is authenticated.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Principal::class.java))
        }
    }
}