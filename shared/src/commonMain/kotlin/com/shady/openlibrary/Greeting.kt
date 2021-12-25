package com.shady.openlibrary

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}