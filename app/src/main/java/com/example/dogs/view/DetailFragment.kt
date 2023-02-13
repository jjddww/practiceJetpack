package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.dogs.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.DogBreed

class DetailFragment : Fragment() {

    private var dogBreed: DogBreed? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.listButton).setOnClickListener { it ->
            val action = DetailFragmentDirections.toListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        arguments?.let {
            dogBreed = DetailFragmentArgs.fromBundle(it).dogData
        }
        view.findViewById<TextView>(R.id.dogName).text = dogBreed?.dogBreed
        view.findViewById<TextView>(R.id.dogLifeSpan).text = dogBreed?.lifeSpan
    }
}