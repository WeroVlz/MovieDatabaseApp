package com.example.practica3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddMovie : Fragment() {

    private val database = Firebase.database
    private val myRef = database.getReference("peliculas")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_pelicula, container, false)
        super.onCreate(savedInstanceState)

        val toolbar = (activity as Principal).findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "Add Movie"

        val addMovie = view.findViewById<Button>(R.id.addMovie)
        val cancelAdd = view.findViewById<Button>(R.id.cancelAdd)
        val movieName = view.findViewById<EditText>(R.id.movieName)
        val movieGenre = view.findViewById<EditText>(R.id.movieGenre)
        val movieYear = view.findViewById<EditText>(R.id.movieYear)

        addMovie.setOnClickListener {
            if(movieName.text.isEmpty() || movieGenre.text.isEmpty() || movieYear.text.isEmpty()){
                Toast.makeText(this.activity, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }else{
                val newMovie = Movie(movieName.text.toString(), movieYear.text.toString(), movieGenre.text.toString())
                myRef.push().setValue(newMovie)

                (context as Principal).replaceFragment(List())
            }
        }
        cancelAdd.setOnClickListener {
            (context as Principal).replaceFragment(List())
        }

        return view
    }

}