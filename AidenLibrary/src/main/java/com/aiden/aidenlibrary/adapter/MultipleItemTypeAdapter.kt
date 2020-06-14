package com.aiden.aidenlibrary.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder

class MultipleItemTypeAdapter<T : MultipleType>(
    mainCommonAdapter: CommonAdapter<T>,
    val list: List<T>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var commonAdapterArray = SparseArray<CommonAdapter<T>>()

    init {
        if (mainCommonAdapter.getType() != null) {
            commonAdapterArray.put(mainCommonAdapter.getType()!!.getItemType(), mainCommonAdapter);
        }
    }

    fun addAdapter(commonAdapter: CommonAdapter<T>): MultipleItemTypeAdapter<T> {
        if (commonAdapter.getType() != null) {
            commonAdapterArray.put(commonAdapter.getType()!!.getItemType(), commonAdapter);
        }
        return this
    }


    override fun getItemViewType(position: Int): Int {
        return list[position].getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return commonAdapterArray[viewType].onCreateViewHolder(parent, viewType)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val t = list[position]
        commonAdapterArray[t.getItemType()].convert(holder as ViewHolder, t)
    }

    override fun getItemCount() = list.size
}
