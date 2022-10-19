package com.example.practica3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import kotlin.collections.ArrayList

class DeleteMovie(pos: Int, movieList: ArrayList<Movies>) : Fragment() {

    private val database = Firebase.database
    private val myRef = database.getReference("peliculas")
    private val position = pos
    private val data = movieList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_delete_movie, container, false)
        super.onCreate(savedInstanceState)

        val toolbar = (activity as Principal).findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "Delete Movie"

        val movieTitle = view.findViewById<TextView>(R.id.movieName)
        val movieGenre = view.findViewById<TextView>(R.id.movieGenre)
        val movieYear = view.findViewById<TextView>(R.id.movieYear)

        movieTitle.text = data[position].name
        movieGenre.text = data[position].genre
        movieYear.text = data[position].year

        val deleteMovie = view.findViewById<Button>(R.id.deleteMovie)
        deleteMovie.setOnClickListener {
            myRef.child(data[position].id.toString()).removeValue()
            Toast.makeText(view.context, "Movie deleted successfully.", Toast.LENGTH_SHORT).show()

            (context as Principal).replaceFragment(List())
        }

        val cancelDelete = view.findViewById<Button>(R.id.cancelDelete)
        cancelDelete.setOnClickListener {
            (context as Principal).replaceFragment(List())
        }

        return view
    }


}