package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import model.DogBreed
import util.getProgressDrawable
import util.loadImage

class DogListAdapter(val clickListener: (View, DogBreed) -> Unit) : ListAdapter<DogBreed, DogListAdapter.DogItemViewHolder> (diffUtil) {

    inner class DogItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val dogImg = itemView.findViewById<ImageView>(R.id.dogImage)
        private val dogName = itemView.findViewById<TextView>(R.id.dogName)
        private val dogLifespan = itemView.findViewById<TextView>(R.id.dogLifeSpan)
        private val rootView = itemView.findViewById<LinearLayout>(R.id.rootView)

        fun bind(dogBreed: DogBreed){
            dogImg.setImageResource(R.drawable.ic_launcher_background)
            dogName.text = dogBreed.dogBreed
            dogLifespan.text = dogBreed.lifeSpan
            dogImg.loadImage(dogBreed.imageUrl, getProgressDrawable(itemView.context))
            rootView.setOnClickListener{
                clickListener(it, dogBreed)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogItemViewHolder {
        return DogItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: DogItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DogBreed>(){
            override fun areItemsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
                return oldItem.breedId == newItem.breedId
            }

            override fun areContentsTheSame(oldItem: DogBreed, newItem: DogBreed): Boolean {
                return oldItem == newItem
            }

        }
    }
}