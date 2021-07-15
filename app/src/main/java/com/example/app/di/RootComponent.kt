package com.example.app.di

import com.example.router.GlobalRouter
import com.example.myapplication.ui.project_a.screens.ProjectAScreen
import com.example.router.ScreenRouterFactoryProvider
import dagger.Component

@Component(
    modules = [
        RootModule::class,
        RouterModule::class,
    ]
)
@RootScope
interface RootComponent {

    fun screenRouterFactoryProvider(): com.example.router.ScreenRouterFactoryProvider

    fun globalRouter(): com.example.router.GlobalRouter<ProjectAScreen>
}