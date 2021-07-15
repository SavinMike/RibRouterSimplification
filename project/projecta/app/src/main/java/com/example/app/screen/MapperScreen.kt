package com.example.app.screen

import com.example.router.Screen
import com.example.screens.ProjectAScreen

object MapperScreen : (Screen) -> ProjectAScreen {
    override fun invoke(screen: Screen): ProjectAScreen =
        when (screen) {
            is ProjectAScreen -> screen
            else -> throw IllegalArgumentException("Unknown screen: $screen")
        }
}