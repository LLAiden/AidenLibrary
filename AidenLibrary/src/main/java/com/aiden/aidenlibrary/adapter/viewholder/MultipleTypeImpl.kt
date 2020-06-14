package com.aiden.aidenlibrary.adapter.viewholder

import com.aiden.aidenlibrary.adapter.MultipleType

class MultipleTypeImpl(private val itemType: Int) : MultipleType {

    override fun getItemType() = itemType
}
