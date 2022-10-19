package com.example.practica3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class List : Fragment() {

    private val database = Firebase.database
    private val myRef = database.getReference("peliculas")
    lateinit var data: ArrayList<Movies>
    lateinit var viewList: View

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        viewList = inflater.inflate(R.layout.fragment_list, container, false)
        super.onCreate(savedInstanceState)

        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.value
                println(value)
                data = ArrayList()
                snapshot.children.forEach{
                        movieChild ->

                    val movie = Movies(movieChild.child("nombre").value.toString(),movieChild.child("anio").value.toString(),
                        movieChild.child("genero").value.toString(), movieChild.key.toString())
                    data.add(movie)
                }
                fillList()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        val toolbar = (activity as Principal).findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "Movie List"

        val addMovie = viewList.findViewById<FloatingActionButton>(R.id.addButton)
        addMovie.setOnClickListener {
            (context as Principal).replaceFragment(AddMovie())
        }

        return viewList
    }

    private fun fillList(){

        val adapter = this.activity?.let { MovieAdapter(it, data) }
        val list = viewList.findViewById<ListView>(R.id.list)
        list.adapter = adapter
    }

}