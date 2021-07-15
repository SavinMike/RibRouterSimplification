package com.example.app.di

import com.example.common.RootDeps
import com.example.ribrouter.RouterDependency

interface RootDependency : RouterDependency {

    fun rootDeps(): RootDeps
}