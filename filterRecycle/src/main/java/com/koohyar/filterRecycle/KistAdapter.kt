package com.koohyar.filterRecycle


/*
class KistAdapter<T>(
    private var list: List<T>,
    private var layout: Int? = R.layout.item_filter,
    private var layoutManager: RecyclerView.LayoutManager? = null,
    private var clickListener: ((item: T, position: Int, view: View) -> Unit)? = null,
    private var longClickListener: ((item: T, position: Int, view: View) -> Unit)? = null,
    private var itemId: ((item: T) -> Long?)? = null,
   // private var binding: ((T, itemView: View) -> Unit)?
) : RecyclerView.Adapter<KistAdapter<T>.ViewHolder>()
{




    //Copy the list so the outside list changes will not affect this one
    init {
        list = list.toMutableList()
        setHasStableIds(itemId != null)
    }

    private var isLoading: Boolean = false
    private var recycleView: RecyclerView? = null
    private var lastListHash: Long = -1L

    */
/**
     * Function/variable used if the user activate the endOfScroll callback
     *//*

   */
/* val endOfScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) {
                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val pastVisibleItems = when (recyclerView.layoutManager) {
                    is LinearLayoutManager -> (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    is GridLayoutManager -> (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                    is SupportLinearLayoutManager -> (recyclerView.layoutManager as SupportLinearLayoutManager).findFirstVisibleItemPosition()
                    is SupportGridLayoutManager -> (recyclerView.layoutManager as SupportGridLayoutManager).findFirstVisibleItemPosition()
                    else -> 3
                }

                val listHash = getListHash()
                if (visibleItemCount + pastVisibleItems >= totalItemCount && lastListHash != listHash) {
                    lastListHash = listHash
                    endOfScroll?.invoke()
                }
            }
        }
    }*//*


    private fun getListHash(): Long {
        var hash = 0L
        list.forEach { hash += it.hashCode() }
        return hash
    }

    */
/**
     * This add come features that can be optional,
     * The first one is a fallback to prevent the creation of a RecycleView without an
     * LayoutManager using the most used (LinearLayoutManager)
     * The second is for the optional argument endOfScroll(), if exist, it will overwrite the
     * RecyclerView.OnScrollListener, so it will detect the end of the scroll and call the lambda
     * to let the user know when to add more elements in the list (infinite list implementation)
     * Check the endOfScrollListener() variable bellow for more
     *//*

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recycleView = recyclerView
        if (recyclerView.layoutManager == null && layoutManager == null) {
            recyclerView.layoutManager = SupportLinearLayoutManager(recyclerView.context)
        }
        if (layoutManager != null) recyclerView.layoutManager = layoutManager

        recyclerView.layoutManager?.isItemPrefetchEnabled = itemId != null

        if (endOfScroll != null) recyclerView.addOnScrollListener(endOfScrollListener)
    }

    fun getItemByIndex(index: Int) = list[index]

    override fun getItemId(position: Int): Long {
        return try {
            if (!hasStableIds() || itemId == null) return RecyclerView.NO_ID
            getItemViewType(position).also { if (it != TYPE.ITEM.ordinal) return it.toLong() }
            itemId?.invoke(list[position]) ?: Random.nextLong()
        } catch (err: Exception) {
            Random.nextLong()
        }
    }




    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recycleView = null
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            listWithLoading && loadingView != null && isLoading && position == countHeader() + list.size -> TYPE.LOADING.ordinal
            emptyLayout != null && !isLoading && list.isEmpty() && position == countHeader() -> TYPE.EMPTY.ordinal
            headerLayout != null && position == 0 -> TYPE.HEADER.ordinal
            footerLayout != null && position == list.size + countEmpty() + countHeader() -> TYPE.FOOTER.ordinal
            else -> TYPE.ITEM.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            TYPE.HEADER.ordinal ->
                HeaderHolder(LayoutInflater.from(parent.context), parent)
            TYPE.ITEM.ordinal ->
                ItemHolder(LayoutInflater.from(parent.context), parent)
            TYPE.EMPTY.ordinal ->
                EmptyHolder(LayoutInflater.from(parent.context), parent)
            TYPE.FOOTER.ordinal ->
                FooterHolder(LayoutInflater.from(parent.context), parent)
            TYPE.LOADING.ordinal ->
                LoadingHolder(LayoutInflater.from(parent.context), parent)
            else -> ItemHolder(LayoutInflater.from(parent.context), parent)
        }

    override fun getItemCount() =
        countHeader() + countEmpty() + list.size + countFooter()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            when (holder) {
                is ItemHolder -> holder.bind(list[position - countHeader()])
                is HeaderHolder -> holder.bind()
                is FooterHolder -> holder.bind()
                is LoadingHolder -> holder.bind()
                is EmptyHolder -> {
                    */
/** Do you really need to change an empty view? **//*

                }
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }


    @Suppress("UNCHECKED_CAST")
    val clickListenerAction = View.OnClickListener { view ->
        try {
            val itemElement = view?.tag as T
            val index = list.indexOfFirst { itemElement == it }
            clickListener?.invoke(itemElement, index, view)
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }

    @Suppress("UNCHECKED_CAST")
    val longClickListenerAction = View.OnLongClickListener { view ->
        try {
            val itemElement = view?.tag as T
            val index = list.indexOfFirst { itemElement == it }
            longClickListener?.invoke(itemElement, index, view)
            true
        } catch (err: Exception) {
            false
        }
    }

    open inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup, holderLayout: Int) :
        RecyclerView.ViewHolder(inflater.inflate(holderLayout, parent, false)) {
        fun bind(item: T) {
            if (clickListener != null || longClickListener != null) {
                itemView.tag = item
                if (clickListener != null)
                    itemView.setOnClickListener(clickListenerAction)
                else
                    itemView.setOnClickListener(null)
                if (longClickListener != null)
                    itemView.setOnLongClickListener(longClickListenerAction)
                else
                    itemView.setOnLongClickListener(null)
            }
            binding?.let { it(item, itemView) }
        }
    }

    inner class ItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
        ViewHolder(inflater, parent, layout)




}*/
