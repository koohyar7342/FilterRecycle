package com.example.testinglibrary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testinglibrary.databinding.ActivityMainBinding
import com.koohyar.filterRecycle.FilterClickListener
import com.koohyar.filterRecycle.FilterModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // lateinit var myAdapter:FiltersAdapter
    private var filtersList = listOf<FilterModel>(

        FilterModel(0, "فیلتر 0", "فیلتر 0", false),
        FilterModel(1, "فیلتر 1", "فیلتر 1", false),
        FilterModel(2, "فیلتر 2", "فیلتر 2", false),
        FilterModel(3, "فیلتر 3", "فیلتر 3", false),
        FilterModel(4, "فیلتر 4", "فیلتر 4", false),
        FilterModel(5, "فیلتر 5", "فیلتر 5", false),
        FilterModel(6, "فیلتر 6", "فیلتر 6", false),
        FilterModel(7, "فیلتر 7", "فیلتر 7", false),
        FilterModel(8, "فیلتر 8", "فیلتر 8", false),
        FilterModel(9, "فیلتر 9", "فیلتر 9", false),
        FilterModel(10, "فیلتر 10", "فیلتر 10", false)
    )
    val mainList = listOf<String>(
        "فیلتر 0",
        "فیلتر 2",
        "فیلتر 3",
        "فیلتر 6",
        "فیلتر 7",
        "فیلتر 9",
        "فیلتر 4",
        "فیلتر 1",
        "فیلتر 5",
        "فیلتر 7",
        "فیلتر 9",
        "فیلتر 10",
        "فیلتر 2",
        "فیلتر 5",
        "فیلتر 7",
        "فیلتر 9",
        "فیلتر 0",
        "فیلتر 2",
        "فیلتر 5",
        "فیلتر 1",
        "فیلتر 3",
        "فیلتر 8",
        "فیلتر 4",
        "فیلتر 9",
        "فیلتر 8",
        "فیلتر 4",
        "فیلتر 2",
        "فیلتر 1",
        "فیلتر 1",
        "فیلتر 4",
        "فیلتر 6",
        "فیلتر 2",
        "فیلتر 9",
        "فیلتر 1",
        "فیلتر 3",
        "فیلتر 9",
        "فیلتر 6",
        "فیلتر 6",
        "فیلتر 5",
        "فیلتر 1",
    )
    val myAdapter = MyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFiltersRecyclerView()
        initMainRecycle()


    }

    private fun initMainRecycle() {
        binding.myRecycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.myRecycle.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.myRecycle.adapter = myAdapter
        myAdapter.submitList(mainList)

    }

    private fun initFiltersRecyclerView() {

        binding.testingRecycle.initializeRecycleView(filtersList, lifecycleScope)

        binding.testingRecycle.setClickListener(object : FilterClickListener {
            override fun onAddFilter(position: Int, id: Int): Boolean {

                filtersList[id].apply { selected=true }

                invokeFilters()

                return true
            }

            override fun onRemoveFilter(position: Int, id: Int): Boolean {
                filtersList[id].apply { selected=false }
                invokeFilters()

                return true
            }
        })


    }

    private fun invokeFilters() {
        val filters = mutableListOf<String>()
        filtersList.filter { it.selected }.forEach { filters.add(it.unselectedTittle)}
        if (filters.isNotEmpty()){
            myAdapter.submitList(mainList.filter { filters.contains(it)})
        } else myAdapter.submitList(mainList)
    }


}