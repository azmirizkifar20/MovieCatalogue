package org.marproject.moviescatalogue.utils.`interface`

import android.view.View

interface AdapterCallback<T> {
    fun initComponent(itemView: View, data: T, itemIndex: Int)
    fun onItemClicked(itemView: View, data: T, itemIndex: Int)
}