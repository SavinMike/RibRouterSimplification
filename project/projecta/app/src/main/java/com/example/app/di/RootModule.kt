package com.example.app.di

import android.util.Log
import com.example.app.FragmentFactoriesDependency
import com.example.app.screen.MapperScreen
import com.example.common.RootDeps
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.router.WrappedGlobalRouter
import com.example.screens.ProjectAScreen
import dagger.Module
import dagger.Provides

@Module
object RootModule {

    @RootScope
    @Provides
    fun rootDependency(globalRouter: GlobalRouter<ProjectAScreen>): RootDependency =
        object : RootDependency {

            init {
                Log.d("Init", "RootDependency")
            }

            private val wrappedGlobalRouter: GlobalRouter<Screen> = WrappedGlobalRouter(globalRouter, MapperScreen)

            override fun rootDeps(): RootDeps = RootDeps()

            override fun globalRouter(): GlobalRouter<Screen> = wrappedGlobalRouter
        }

    @RootScope
    @Provides
    fun screenFactoryDependencyResolver(rootDependency: RootDependency): ScreenFactoryDependencyProvider =
        ScreenFactoryDependencyProvider(rootDependency)

    @RootScope
    @Provides
    fun fragmentFactoriesDependency(rootDependency: RootDependency): FragmentFactoriesDependency =
        FragmentFactoriesDependency(rootDependency)
}