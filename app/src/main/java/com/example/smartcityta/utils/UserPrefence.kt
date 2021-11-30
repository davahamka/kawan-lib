package com.example.smartcityta.utils

import android.content.Context
import com.example.smartcityta.utils.UserPrefence.Companion.ID

internal class UserPrefence(context:Context){
    companion object{
        private const val PREFS_NAME = "user_pref"
        private const val ID = "id"
    }

    private val prefences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(id:String){
        val editor = prefences.edit()

        editor.putString(ID, id)

        editor.apply()
    }

    fun getUser(): String? {

        return prefences.getString(ID, "")
    }
}