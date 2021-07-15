package com.example.router

interface GlobalRouter<S : Screen> {

    fun root(screen: S)

    fun addScreen(screen: S)

    fun replaceScreen(screen: S)

    fun pop()
}