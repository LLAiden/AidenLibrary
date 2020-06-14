package com.aiden.library

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aidenlibrary.adapter.CommonAdapter
import com.aiden.aidenlibrary.adapter.MultipleItemTypeAdapter
import com.aiden.aidenlibrary.adapter.viewholder.MultipleTypeImpl
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = RecyclerView(this)
        setContentView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val mutableListOf = mutableListOf<User>()
        for (index in 1..100) {
            val user = User()
            user.type = index % 6
            Log.e("CommonAdapter", "onCreate>>>27     user.type == "+user.type)
            user.username = UUID.randomUUID().toString().replace("-", "").substring(0, 20)
            mutableListOf.add(user)
        }
        val adapter = MultipleItemTypeAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(0)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.MAGENTA)
            }
        }, mutableListOf).addAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(1)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.RED)
            }
        }).addAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(2)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.GREEN)
            }
        }).addAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(3)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.BLUE)
            }
        }).addAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(4)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.YELLOW)
            }
        }).addAdapter(object :
            CommonAdapter<User>(this, android.R.layout.simple_list_item_1, MultipleTypeImpl(5)) {
            override fun convert(holder: ViewHolder, t: User) {
                holder.setText(android.R.id.text1, t.username + ">>>" + t.type)
                    .setBackgroundColor(android.R.id.text1, Color.YELLOW)
            }
        })

        recyclerView.adapter = adapter
    }
}
