package com.example.ribrouter

import com.example.router.GlobalRouter
import com.example.router.Screen

interface RouterDependency {
    fun globalRouter(): GlobalRouter<Screen>
}