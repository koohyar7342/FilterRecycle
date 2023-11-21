package com.koohyar.filterRecycle

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope

class FilterRecycle(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {

    private var layoutItem: Int = 0
   // var onClickListener: (suspend (position:Int,id: Int) -> Boolean)? = null
    var onLongClickListener: ((item: Any, position: Int, view: View) -> Unit)? = null
    private var isGrid: Boolean = false
    private var spanCount: Int = 2
    var listWithLoading : Boolean = false
    var currentFilters = mutableListOf<String>()

    private var adapter: FiltersAdapter? = null
    lateinit var myAttrs: AttributeSet
     private var colors:HashMap<String,Int> = hashMapOf()


/*    private val layoutManager by lazy {
        if (isGrid) SupportGridLayoutManager(context, spanCount) else SupportLinearLayoutManager(
            context
        )
    }*/

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.FilterRecycle, 0, 0).apply {
            val selectedBackground = getResourceId(R.styleable.FilterRecycle_fr_selected_background_color, android.R.color.holo_orange_light)
            val selectedText = getResourceId(R.styleable.FilterRecycle_fr_selected_text_color, android.R.color.holo_red_light)
            val unSelectedBackground = getResourceId(R.styleable.FilterRecycle_fr_unselected_Background_color, R.color.white)
            val unSelectedText = getResourceId(R.styleable.FilterRecycle_fr_unselected_text_color, R.color.black)
            val cardContentPadding = getResourceId(R.styleable.FilterRecycle_fr_card_content_padding, R.dimen.default_content_padding)

            colors["selectedBackground"] = selectedBackground
            colors["selectedText"] = selectedText
            //colors["selectedStroke"] = selectedStroke
            colors["unSelectedBackground"] = unSelectedBackground
            colors["unSelectedText"] = unSelectedText
            colors["cardContentPadding"] = cardContentPadding
           // colors["unSelectedStroke"] = unSelectedStroke


            recycle()
        }
        myAttrs = attrs
        layoutItem = R.layout.item_filter
        layoutManager =
            LinearLayoutManager(context, HORIZONTAL, true)

    }

/*    fun add(vararg items: Any) {
        if (adapter == null) {
            adapter = FiltersAdapter(
                mContext = context,
                layoutManager = layoutManager,
                itemClickListener = onClickListener

            )
            setAdapter(adapter)
        }
    }*/

    public fun initializeRecycleView(list: List<FilterModel>, coroutineScope: CoroutineScope) {
        if (adapter == null){
            adapter = FiltersAdapter(mContext = context, colors = colors, layoutManager = layoutManager,
                coroutineScope

            )
            setAdapter(adapter)
        }
        adapter?.submitList(list)
    }

    fun notifyItemChanged(adapterPosition: Int) {
        adapter!!.notifyItemChanged(adapterPosition)

    }
    fun setClickListener(onClickListener: FilterClickListener){
        adapter!!.itemClickListener = onClickListener
    }

    fun setSelectedFilter(id:Int){
        adapter?.setSelected(id)
    }
    fun removeSelectedFilter(id:Int){
        adapter?.removeSelected(id)
    }


    /*val filtersClickListener = object : FilterClickListener {
        override fun onAdd(position: Int, id: Int) {
            val dd = AlertDialog.Builder(context)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    adapter!!.currentList[position].selected = true
                    currentFilters.add(adapter!!.currentList[position].unselectedTittle)

                    // myAdapter.notifyItemChanged(position)
                    adapter!!.submitList( adapter!!.currentList.sortedBy { it.id }.sortedByDescending { it.selected })
                    // myAdapter.submitList(filtersList)
                    //val result = setFiltersOnJobsList()
                    Handler(Looper.getMainLooper()).postDelayed({
                        smoothScrollToPosition(0)

                        //Log.d("UpdateJobs", "size: ${result.size}")
                        //adapter.submitList(result)
                    }, 350)

                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    adapter!!.currentList[position].selected = false
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