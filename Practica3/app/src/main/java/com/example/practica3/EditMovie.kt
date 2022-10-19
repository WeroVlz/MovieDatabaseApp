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


class EditMovie(pos: Int, movieList: ArrayList<Movies>) : Fragment() {

    private val database = Firebase.database
    private val myRef = database.getReference("peliculas")
    private val position = pos
    private val data = movieList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_pelicula, container, false)
        super.onCreate(savedInstanceState)

        val toolbar = (activity as Principal).findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "Edit Movie"

        val movieTitle = view.findViewById<EditText>(R.id.movieName)
        val movieGenre = view.findViewById<EditText>(R.id.movieGenre)
        val movieYear = view.findViewById<EditText>(R.id.movieYear)

        movieTitle.setText(data[position].name.toString().uppercase())
        movieGenre.setText(data[position].genre)
        movieYear.setText(data[position].year)

        val editMovie = view.findViewById<Button>(R.id.editMovie)
        editMovie.setOnClickListener {
            val newMovieTitle = movieTitle.text.toString()
            val newMovieGenre = movieGenre.text.toString()
            val newMovieYear = movieYear.text.toString()

            if(newMovieTitle.isEmpty() || newMovieGenre.isEmpty() || newMovieYear.isEmpty()){
                Toast.makeText(this.activity, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }else{
                val newMovie = Movie(newMovieTitle, newMovieYear, newMovieGenre)

                myRef.child(data[position].id.toString()).setValue(newMovie)
                Toast.makeText(view.context, "Movie edited successfully.", Toast.LENGTH_SHORT).show()

                (context as Principal).replaceFragment(List())
            }
        }

        val cancelEdit = view.findViewById<Button>(R.id.cancelEdit)
        cancelEdit.setOnClickListener {
            (context as Principal).replaceFragment(List())
        }

        return view
    }


}