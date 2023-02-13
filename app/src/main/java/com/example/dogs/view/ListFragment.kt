package com.example.dogs.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var dogList: RecyclerView
    private lateinit var dogAdapter: DogListAdapter
    private lateinit var viewModel: ListViewModel
    private lateinit var errorMessage: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorMessage = view.findViewById(R.id.error_msg)
        progressBar = view.findViewById(R.id.loadingProgress)
        dogList = view.findViewById(R.id.dogList)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()

        dogList.apply {
            dogAdapter = DogListAdapter(clickListener = { view, data ->
                val action = ListFragmentDirections.toDetailFragment(data)
                Navigation.findNavController(view).navigate(action)
            })
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = dogAdapter
        }

//        arguments?.let {
//            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
//            Log.d("zzzz", dogUuid.toString())
//        }

//        view.findViewById<FloatingActionButton>(R.id.detailButton).setOnClickListener {
//            val action = ListFragmentDirections.toDetailFragment()
//            Navigation.findNavController(it).navigate(action)
//        }

        observableViewModel()
    }

    private fun observableViewModel(){
        viewModel.dogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogs?.let{
                dogList.visibility = View.VISIBLE
                dogAdapter.submitList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer{
            errorMessage.visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }
}