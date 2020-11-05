package org.marproject.moviescatalogue.utils.`interface`

import androidx.recyclerview.widget.RecyclerView
import org.marproject.moviescatalogue.utils.adapter.AdapterUtils

interface AdapterUtilsInterface<T> {
    // set layout
    fun setLayout(layout: Int): AdapterUtils<T>

    // append data
    fun addData(items: List<T>): AdapterUtils<T>

    // realtime change
    fun updateData(item: T): AdapterUtils<T>

    // adapter callback
    fun adapterCallback(adapterCallback: AdapterCallback<T>): AdapterUtils<T>

    // layout orientation
    fun isVerticalView(): AdapterUtils<T>
    fun isHorizontalView(): AdapterUtils<T>

    // build recyclerview
    fun build(recyclerView: RecyclerView): AdapterUtils<T>
}