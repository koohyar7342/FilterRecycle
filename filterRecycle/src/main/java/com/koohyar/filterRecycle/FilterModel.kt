package com.koohyar.filterRecycle

open class FilterModel(
    var id: Int = 0, var unselectedTittle: String = "",
    var selectedTittle: String = "", var selected: Boolean = false
) {


    public override fun equals(other: Any?): Boolean {
        other as FilterModel
        return (other.id == id)
                && (other.unselectedTittle == unselectedTittle)
                && (other.selectedTittle == selectedTittle)
                && (selected == other.selected)
        //return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + unselectedTittle.hashCode()
        result = 31 * result + selectedTittle.hashCode()
        result = 31 * result + selected.hashCode()
        return result
    }


}

