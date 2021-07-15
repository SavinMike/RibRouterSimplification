package com.example.app.di

import com.example.app.screen.ScreenARouterFactory
import com.example.app.screen.ScreenBRouterFactory
import com.example.app.screen.ScreenCRouterFactory
import com.example.app.screen.ScreenERouterFactory
import com.example.fragmentrib.fragment_container.FragmentContainerRouterFactory

class ScreenFactoryDependencyProvider(private val rootDependency: RootDependency) :
    ScreenARouterFactory.Dependency,
    ScreenBRouterFactory.Dependency,
    ScreenCRouterFactory.Dependency,
    ScreenERouterFactory.Dependency,
    FragmentContainerRouterFactory.Dependency,
    RootDependency by rootDependency