package com.koohyar.filterRecycle

interface FilterClickListener {
     fun onAddFilter(position:Int,id: Int):Boolean?
     fun onRemoveFilter(position:Int,id: Int):Boolean?

}