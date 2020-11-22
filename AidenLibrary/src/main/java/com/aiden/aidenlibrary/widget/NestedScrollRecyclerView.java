package com.aiden.aidenlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NestedScrollRecyclerView extends RecyclerView {

    private static final String TAG = "NestedScrollRecyclerView";
    private float pressDownY = 0;


    public NestedScrollRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
}
