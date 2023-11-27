package com.koohyar.filterRecycle

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koohyar.filterRecycle.databinding.ItemFilterBinding
import com.koohyar.filterRecycle.databinding.ItemFilterSelectedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FiltersAdapter(
    private var mContext: Context,
    private var colors: HashMap<String, Int>,
    //private var layout: Int? = R.layout.item_filter,
    private var layoutManager: RecyclerView.LayoutManager? = null,
    // private var itemClickListener:FilterClickListener?,
    //private var itemClickListener: (suspend (position: Int, id: Int) -> Boolean)? = null,
    private var coroutineScope: CoroutineScope
    //private var longClickListener: ((item: T, position: Int, view: View) -> Unit)? = null,


) :
    ListAdapter<FilterModel, RecyclerView.ViewHolder>(diffUtilCallback) {
    private var recycleView: FilterRecycle? = null
    //var onClickListener: ((position:Int,id: Int) -> Unit)? = null

    var itemClickListener: FilterClickListener? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recycleView = recyclerView as FilterRecycle
        if (layoutManager != null) recyclerView.layoutManager = layoutManager

        /*        if (recyclerView.layoutManager == null && layoutManager == null) {
                    recyclerView.layoutManager = SupportLinearLayoutManager(recyclerView.context)
                }
                if (layoutManager != null) recyclerView.layoutManager = layoutManager

                recyclerView.layoutManager?.isItemPrefetchEnabled = itemId != null

                if (endOfScroll != null) recyclerView.addOnScrollListener(endOfScrollListener)*/
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recycleView = null
    }

    private lateinit var binding: ItemFilterBinding
    private lateinit var bindingSelected: ItemFilterSelectedBinding

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<FilterModel>() {
            override fun areItemsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
                return (oldItem.id == newItem.id)//&&(oldItem.selected == newItem.selected)
            }

            override fun areContentsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean {
                return oldItem == newItem
            }


        }
        // var currentFilters = mutableListOf<String>()

        const val SELECTED = 1
        const val UNSELECTED = 2
    }


    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].selected) SELECTED
        else UNSELECTED
    }

    override fun submitList(list: MutableList<FilterModel>?) {
        super.submitList(list?.sortedBy { it.id }?.sortedByDescending { it.selected }
            ?.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            UNSELECTED -> {
                binding = ItemFilterBinding.inflate(LayoutInflater.from(mContext), parent, false)
                FilterViewHolder(binding)
            }

            SELECTED -> {
                bindingSelected =
                    ItemFilterSelectedBinding.inflate(LayoutInflater.from(mContext), parent, false)
                FilterSelectedViewHolder(bindingSelected)
            }

            else -> {
                binding = ItemFilterBinding.inflate(LayoutInflater.from(mContext), parent, false)
                FilterViewHolder(binding)
            }
        }


    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterPosition = holder.bindingAdapterPosition
        val item = getItem(holder.bindingAdapterPosition)
        when (holder) {
            is FilterViewHolder -> {
                holder.bind(
                    item,
                    adapterPosition,
                    mContext,
                    itemClickListener,
                    currentList,
                    recycleView,
                    coroutineScope,
                    colors

                )
            }

            is FilterSelectedViewHolder -> {
                holder.bind(
                    item,
                    adapterPosition,
                    mContext,
                    itemClickListener,
                    currentList,
                    recycleView,
                    coroutineScope,
                    colors

                )
            }
        }


    }

    fun setSelected(id: Int) {
        currentList.first { it.id == id }.selected = true
        val position = currentList.indexOf(currentList.first { it.id == id })
        recycleView!!.notifyItemChanged(position)
        recycleView!!.initializeRecycleView(currentList.sortedBy { it.id }
            .sortedByDescending { it.selected }, coroutineScope)
        Handler(Looper.getMainLooper()).postDelayed({
            recycleView!!.smoothScrollToPosition(0)
            //notifyDataSetChanged()
        }, 350)
    }

    fun removeSelected(id: Int) {
        currentList.first { it.id == id }.selected = false
        val position = currentList.indexOf(currentList.first { it.id == id })
        recycleView!!.notifyItemChanged(position)
        recycleView!!.initializeRecycleView(currentList.sortedBy { it.id }
            .sortedByDescending { it.selected }, coroutineScope)
        Handler(Looper.getMainLooper()).postDelayed({
            recycleView!!.smoothScrollToPosition(0)
            //notifyDataSetChanged()
        }, 350)
    }
    /*
        val clickListenerAction = View.OnClickListener { view ->
            try {
                val itemElement = view?.tag as T
                val index = currentList.indexOfFirst { itemElement == it }
                clickListener?.invoke(itemElement, index, view)
            } catch (err: Exception) {
                err.printStackTrace()
            }
        }*/

    /*    val longClickListenerAction = View.OnLongClickListener { view ->
            try {
                val itemElement = view?.tag as T
                val index = currentList.indexOfFirst { itemElement == it }
                //longClickListener?.invoke(itemElement, index, view)
                true
            } catch (err: Exception) {
                false
            }
        }*/


    class FilterViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            item: FilterModel,
            position: Int,
            mContext: Context,
            itemClickListener: FilterClickListener? = null,
            currentList: MutableList<FilterModel>,
            recycleView: FilterRecycle?,
            theScope: CoroutineScope,
            colors: HashMap<String, Int>,


            ) {
            /*
             selectedBackground
             selectedText
             selectedStroke
             unSelectedBackground
             unSelectedText
             unSelectedStroke
            */
                        val padding: Int =
                            mContext.resources.getDimension(colors["cardContentPadding"]!!).toInt()
                        binding.itemFilterRootCard.setContentPadding(padding, padding, padding, padding)
                        binding.itemFilterRemove.visibility = View.GONE
                        binding.itemFilterTittle.setTextColor(mContext.getColor(colors["unSelectedText"]!!))
                        binding.itemFilterRootCard.setCardBackgroundColor(mContext.getColor(colors["unSelectedBackground"]!!))


            binding.itemFilterTittle.text = item.unselectedTittle
            binding.root.setOnClickListener {


                theScope.launch {
                    val bbb: Boolean = itemClickListener!!.onAddFilter(position, item.id)
                    withContext(Dispatchers.Main) {
                        if (bbb) {
                            currentList[position].selected = true
                            recycleView!!.notifyItemChanged(position)
                            recycleView.setList(currentList)
                            Handler(Looper.getMainLooper()).postDelayed({
                                recycleView.smoothScrollToPosition(0)

                                //notifyDataSetChanged()
                            }, 350)

                            /*       recycleView!!.initializeRecycleView(currentList.sortedBy { it.id }
                                       .sortedByDescending { it.selected }, theScope)*/


                        }

                    }

                }


            }
            // mContext.getLifeCycleOwner()?.lifecycleScope?.launch {  }
        }


    }

    class FilterSelectedViewHolder(private val binding: ItemFilterSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            item: FilterModel,
            position: Int,
            mContext: Context,
            itemClickListener: FilterClickListener? = null,
            currentList: MutableList<FilterModel>,
            recycleView: FilterRecycle?,
            theScope: CoroutineScope,
            colors: HashMap<String, Int>,


            ) {
            /*
             selectedBackground
             selectedText
             selectedStroke
             unSelectedBackground
             unSelectedText
             unSelectedStroke
            */
                        val padding: Int =
                            mContext.resources.getDimension(colors["cardContentPadding"]!!).toInt()
                        binding.itemFilterRootCard.setContentPadding(padding, padding, padding, padding)
                        binding.itemFilterRemove.visibility = View.VISIBLE
                        binding.itemFilterTittle.setTextColor(mContext.getColor(colors["selectedText"]!!))
                        binding.itemFilterRootCard.setCardBackgroundColor(mContext.getColor(colors["selectedBackground"]!!))

            binding.itemFilterTittle.text = item.selectedTittle
            binding.itemFilterRemove.setOnClickListener {
                theScope.launch {
                    val bbb = itemClickListener!!.onRemoveFilter(position, item.id)

                    withContext(Dispatchers.Main) {
                        if (bbb) {
                            currentList[position].selected = false
                            recycleView!!.notifyItemChanged(position)
                            recycleView.setList(currentList)
                            /*       recycleView!!.initializeRecycleView(currentList.sortedBy { it.id }
                                       .sortedByDescending { it.selected }, theScope)*/
                            Handler(Looper.getMainLooper()).postDelayed({
                                recycleView.smoothScrollToPosition(0)
                            }, 350)
                        }

                    }


                }
            }

        }


    }

}