package com.aiden.library

import com.aiden.aidenlibrary.adapter.MultipleType

class User : MultipleType {

    var type = 0
    var username = ""

    override fun getItemType() = type
}
