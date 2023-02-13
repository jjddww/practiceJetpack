package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dogs.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.DogBreed
import viewmodel.DetailViewModel
import viewmodel.ListViewModel

class DetailFragment : Fragment() {

    private var dogBreed: DogBreed? = null
    private lateinit var viewModel: DetailViewModel
    private lateinit var dogName: TextView
    private lateinit var dogLifeSpan: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogName = view.findViewById(R.id.dogName)
        dogLifeSpan = view.findViewById(R.id.dogLifeSpan)
        view.findViewById<FloatingActionButton>(R.id.listButton).setOnClickListener { it ->
            val action = DetailFragmentDirections.toListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.fetch()

        observeViewModel()

//        arguments?.let {
//            dogBreed = DetailFragmentArgs.fromBundle(it).dogData
//        }
//        view.findViewById<TextView>(R.id.dogName).text = dogBreed?.dogBreed
//        view.findViewById<TextView>(R.id.dogLifeSpan).text = dogBreed?.lifeSpan
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer {
            dogName.text = it.dogBreed
            dogLifeSpan.text = it.lifeSpan
        })
    }
}