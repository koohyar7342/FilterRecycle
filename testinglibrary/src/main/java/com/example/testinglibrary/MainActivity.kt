package com.example.testinglibrary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.testinglibrary.databinding.ActivityMainBinding
import com.koohyar.filterRecycle.FilterClickListener
import com.koohyar.filterRecycle.FilterModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // lateinit var myAdapter:FiltersAdapter
    var filtersList = listOf<FilterModel>(

        FilterModel(0, "فیلتر 0"),
        FilterModel(1, "فیلتر 1"),
        FilterModel(2, "فیلتر 2"),
        FilterModel(3, "فیلتر 3"),
        FilterModel(4, "فیلتر 4"),
        FilterModel(5, "فیلتر 5"),
        FilterModel(6, "فیلتر 6"),
        FilterModel(7, "فیلتر 7"),
        FilterModel(8, "فیلتر 8"),
        FilterModel(9, "فیلتر 9"),
        FilterModel(10, "فیلتر 10"),
        /*        FilterModel("فیلتر 11"),
                FilterModel("فیلتر 12"),
                FilterModel("فیلتر 13"),
                FilterModel("فیلتر 14"),
                FilterModel("فیلتر 15"),
                FilterModel("فیلتر 16"),
                FilterModel("فیلتر 17"),
                FilterModel("فیلتر 18"),
                FilterModel("فیلتر 19"),*/
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFiltersRecyclerView()


    }

    private fun initFiltersRecyclerView() {

        // binding.testingRecycle.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, true)

        /*      myAdapter = FiltersAdapter(
                  this,
                  filtersClickListener
              )*/
        binding.testingRecycle.initializeRecycleView(filtersList, lifecycleScope)

        binding.testingRecycle.setClickListener(object : FilterClickListener {
            override fun onAddFilter(position: Int, id: Int): Boolean? {
                Toast.makeText(this@MainActivity, "onAdd", Toast.LENGTH_SHORT).show()
                Log.d("testingRecycle", "id: $id")

                binding.testingRecycle.setSelectedFilter(id)
                return null
            }

            override fun onRemoveFilter(position: Int, id: Int): Boolean? {

                Toast.makeText(this@MainActivity, "onRemove", Toast.LENGTH_SHORT).show()
                Log.d("testingRecycle", "id: $id")

                binding.testingRecycle.removeSelectedFilter(id)
                return null
            }
        })



    }



    /*    val filtersClickListener = object : FilterClickListener {
            override fun onAdd(position: Int, id: Int) {
                val dd = AlertDialog.Builder(this@MainActivity)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        filtersList[position].selected = true
                        currentFilters.add(filtersList[position].unselectedTittle)

                        // myAdapter.notifyItemChanged(position)
                        filtersList = filtersList.sortedBy { it.id }.sortedByDescending { it.selected }
                        // myAdapter.submitList(filtersList)
                        //val result = setFiltersOnJobsList()
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.testingRecycle.smoothScrollToPosition(0)
                        }, 350)

                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        filtersList[position].selected = false
                        //  myAdapter.notifyItemChanged(position)
                        // filtersList = filtersList.sortedBy { it.id }
                        filtersList = filtersList.sortedBy { it.id }.sortedByDescending { it.selected }
                        //  myAdapter.submitList(filtersList)
                        binding.testingRecycle.smoothScrollToPosition(0)
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.testingRecycle.smoothScrollToPosition(0)
                        }, 500)
                    }
                    .setCancelable(true)

                dd.show()


            }

            override fun onRemove(position: Int, id: Int) {
                *//*            filtersList[position].selected = false
                        filtersAdapter.notifyItemChanged(position)

                        currentFilters[id] = ""
                        Log.d("UpdateJobs", "selectedId: ${currentFilters}")
                        *//**//*            when (id) {
                            0 -> {
                                currentFilters[] = ""
                            }
                            1 -> {
                                currentFilters[] = ""
                            }
                            2 -> {
                                currentFilters[] = ""
                            }
                            3 -> {
                                currentFilters[] = ""
                            }
                        }*//**//*


            // filtersList = filtersList.sortedBy { it.id }
            filtersList = filtersList.sortedBy { it.id }.sortedByDescending { it.selected }
            filtersAdapter.submitList(filtersList)


            val result = setFiltersOnJobsList()




            binding.filterRecycle.smoothScrollToPosition(0)
            Handler(Looper.getMainLooper()).postDelayed({
                Log.d("UpdateJobs", "size: ${result.size}")
                adapter.submitList(result)
                //adapter.notifyDataSetChanged()

                binding.filterRecycle.smoothScrollToPosition(0)
            }, 500)*//*
        }

    }*/
}