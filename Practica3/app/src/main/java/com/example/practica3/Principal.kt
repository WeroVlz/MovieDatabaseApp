package com.example.practica3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


class Principal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        auth = FirebaseAuth.getInstance()

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val user = auth.currentUser
        if(item.itemId== R.id.salir){
            Toast.makeText(this, "Signed Out",Toast.LENGTH_LONG).show()
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            //finish()
        }else if(item.itemId== R.id.perfil){
            if (user != null) {
                Toast.makeText(this, "Profile: "+ user.email.toString(),Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val change = manager.beginTransaction()
        change.replace(R.id.fragment, fragment)
        change.commit()
    }
}