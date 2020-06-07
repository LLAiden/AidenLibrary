package com.aiden.aidenlibrary.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aidenlibrary.adapter.listener.OnItemClickListener
import com.aiden.aidenlibrary.adapter.listener.OnItemLongClickListener
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder

abstract class CommonAdapter<T>(private val context: Context, @param:LayoutRes private val layoutId: Int, private val dataList: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null
    private var itemLongClickListener: OnItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = View.inflate(context, layoutId, null)
        Log.e("CommonAdapter", "Create Vie wHolder...")
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        convert(holder as ViewHolder, dataList[position])
        holder.itemView.setOnClickListener { itemClickListener?.onItemClick(it, position) }
        holder.itemView.setOnLongClickListener {
            itemLongClickListener?.onItemLongClick(it, position) ?: false
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun convert(holder: ViewHolder, t: T)

    fun setOnItemClickListener(itemClickListener: OnItemClickListener): CommonAdapter<T> {
        this.itemClickListener = itemClickListener
        return this
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener): CommonAdapter<T> {
        this.itemLongClickListener = itemLongClickListener
        return this
    }
}