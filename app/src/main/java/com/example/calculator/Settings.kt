package com.example.calculator

import android.content.Context
object Settings {
    private const val THEME = "chosen_theme"
    private const val KEY_THEME = "key_theme"

    fun saveTheme(context: Context, theme: Int){
        context.getSharedPreferences(THEME,Context.MODE_PRIVATE).edit().putInt(KEY_THEME,theme).apply()
    }
    fun loadTheme(context: Context): Int {
        return context.getSharedPreferences(THEME,Context.MODE_PRIVATE).getInt(THEME,1)
    }
}