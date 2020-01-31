package com.example.epoxy.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.random.Random

data class User(
    val name: String,
    val email : String,
    val age : Int
){
    companion object{
        fun getSampleUsers(): MutableList<User>{
            val userList : MutableList<User> = mutableListOf()
            for (i in 1..15){
                userList.add(User(name = "User $i", email = "user$i@email.com", age = Random.nextInt(18,54)))
            }
            return userList
        }
    }
}