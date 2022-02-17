package com.example.singletonexample

import com.example.singletonexample.models.User

object ExampleSingleton {
    val singletonUser: User by lazy {
        User("jacobpzinn@gmail.com", "Airbourne25", "image.png")
    }
}