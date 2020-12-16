package com.aiden.library

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aidenlibrary.adapter.CommonAdapter
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder
import com.aiden.aidenlibrary.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : BaseActivity() {

    override val contentView = R.layout.activity_second

    override fun initView() {
        Glide.with(this)
            .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608139944781&di=0f05c8c3ec5d23e3f7aa948243145f0f&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F130223%2F240474-1302230R04153.jpg")
            .into(image)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val arrayList = ArrayList<String>(100)
        for (i in 1 until 100){
            arrayList.add(UUID.randomUUID().toString())
        }
        mRecyclerView.adapter = object:CommonAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList){
            override fun convert(holder: ViewHolder, t: String) {
                holder.setText(android.R.id.text1,t)
            }
        }
    }
}