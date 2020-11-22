package com.aiden.library;

import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiden.aidenlibrary.adapter.CommonAdapter;
import com.aiden.aidenlibrary.adapter.viewholder.ViewHolder;
import com.aiden.aidenlibrary.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SecondActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_second;
    }

    @Override
    public void initView() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(UUID.randomUUID().toString());
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(this, android.R.layout.simple_list_item_1,strings){
            @Override
            public void convert(@NotNull ViewHolder holder, String s) {
                holder.setText(android.R.id.text1,s);
            }
        });
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        layoutParams.height = getResources().getDisplayMetrics().heightPixels;
        recyclerView.setLayoutParams(layoutParams);
    }
}
