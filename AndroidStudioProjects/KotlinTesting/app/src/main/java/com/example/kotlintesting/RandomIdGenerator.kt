package com.example.kotlintesting
import java.util.*

class RandomIdGenerator {


        companion object {
            fun generateUniqueId(): Int {
                val idOne = UUID.randomUUID()
                var str = "" + idOne
                val uid = str.hashCode()
                val filterStr = "" + uid
                str = filterStr.replace("-".toRegex(), "")
                return str.toInt()
            }
        }
    }