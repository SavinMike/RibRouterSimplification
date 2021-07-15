package com.example.app.di

import androidx.fragment.app.FragmentManager
import com.example.app.Connector
import com.example.app.FragmentFactoriesDependency
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.router.GlobalRouter
import com.example.screens.ProjectAScreen
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RootModule::class,
        RouterModule::class,
    ]
)
@RootScope
interface RootComponent {

    fun screenRouterFactoryProvider(): ScreenRouterFactoryProvider

    fun fragmentFactoriesDependency(): FragmentFactoriesDependency

    fun globalRouter(): GlobalRouter<ProjectAScreen>

    fun connector(): Connector

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragmentManager: FragmentManager,
        ): RootComponent
    }
}