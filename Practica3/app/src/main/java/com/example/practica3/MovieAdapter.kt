package com.example.practica3

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieAdapter(private val context: Activity, private val arrayList: ArrayList<Movies>): ArrayAdapter<Movies>(context, R.layout.item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item, null)



        view.findViewById<TextView>(R.id.name).text = arrayList[position].name
        view.findViewById<TextView>(R.id.year).text = arrayList[position].year
        view.findViewById<TextView>(R.id.genre).text = arrayList[position].genre


        val editMovie = view.findViewById<FloatingActionButton>(R.id.edit)
        editMovie.setOnClickListener {
            (context as Principal).replaceFragment(EditMovie(position, arrayList))
        }

        val deleteMovie = view.findViewById<FloatingActionButton>(R.id.delete)
        deleteMovie.setOnClickListener{
            (context as Principal).replaceFragment(DeleteMovie(position, arrayList))
        }

        return view
    }


}