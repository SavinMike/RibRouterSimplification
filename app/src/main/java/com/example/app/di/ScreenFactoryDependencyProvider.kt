package com.example.app.di

import com.example.myapplication.ui.project_a.screens.ScreenARouterFactory

class ScreenFactoryDependencyProvider(rootDependency: RootDependency) :
    ScreenARouterFactory.Dependency,
    RootDependency by rootDependency