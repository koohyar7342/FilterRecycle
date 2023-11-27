package com.example.testinglibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testinglibrary.databinding.TextRowItemBinding
import com.koohyar.filterRecycle.FilterModel
import com.koohyar.filterRecycle.FiltersAdapter
import com.koohyar.filterRecycle.databinding.ItemFilterBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class MyAdapter(private val myContext: Context) :
    ListAdapter<String, RecyclerView.ViewHolder>(diffUtilCallback) {
    private lateinit var binding: TextRowItemBinding


    companion object{
        val diffUtilCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return (oldItem == newItem)//&&(oldItem.selected == newItem.selected)
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }


        }
    }
    class ViewHolder(private val binding : TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(title:String){
            binding.textView.text = title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = TextRowItemBinding.inflate(LayoutInflater.from(myContext), viewGroup, false)

        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(currentList[position])
    }



}
