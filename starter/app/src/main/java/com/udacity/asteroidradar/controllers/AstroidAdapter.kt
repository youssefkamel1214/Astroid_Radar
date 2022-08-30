package com.udacity.asteroidradar.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AstroidItemBinding
//this adpater used to update recler view in main fragment adapter  we used list adapter for live updates
class AstroidAdapter (val clickListener: AstroidListhner):ListAdapter<Asteroid, AstroidAdapter.Viewholder>(AstroidDiffCallback()) {
    class Viewholder(binding: AstroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: AstroidItemBinding
        val res = binding.image.context.resources
        init {
            this.binding = binding
        }

        fun bind(item: Asteroid, clickListener: AstroidListhner) {
            binding.astroid = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    class AstroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }
    class AstroidListhner(val clickListener: (astroid:Asteroid) -> Unit) {
        fun onClick(astroiditem:Asteroid) = clickListener(astroiditem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            AstroidItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val ast=getItem(position)
        holder.bind(ast,clickListener)
    }
}