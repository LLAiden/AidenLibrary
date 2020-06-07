package com.aiden.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aidenlibrary.adapter.CommonAdapter
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = RecyclerView(this)
        setContentView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val mutableListOf = mutableListOf<String>()
        for (index in 0..100) {
            mutableListOf.add(UUID.randomUUID().toString())
        }
        val adapter = object : CommonAdapter<String>(this, android.R.layout.simple_list_item_1, mutableListOf) {
            override fun convert(holder: ViewHolder, t: String) {
                holder.setText(android.R.id.text1, t)
            }
        }
        recyclerView.adapter = adapter
    }
}