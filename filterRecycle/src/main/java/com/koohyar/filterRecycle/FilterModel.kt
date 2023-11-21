package com.koohyar.filterRecycle

open class FilterModel(  var id:Int = 0, var unselectedTittle:String = "",
                      var selectedTittle:String = "", var selected: Boolean = false){


    public override fun equals(other: Any?): Boolean {
        other as FilterModel
        return (unselectedTittle == other.unselectedTittle) && (selected == other.selected)
        //return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = unselectedTittle.hashCode()
        result = 31 * result + selectedTittle.hashCode()
        result = 31 * result + selected.hashCode()
        return result
    }
}

