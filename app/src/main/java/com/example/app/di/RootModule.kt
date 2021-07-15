package com.example.app.di

import com.example.myapplication.ui.project_a.root.RootDeps
import com.example.myapplication.ui.project_a.screens.ProjectAScreenBuilderFactory
import com.example.router.ScreenRouterFactoryProvider
import dagger.Module
import dagger.Provides

@Module
object RootModule {

    @RootScope
    @Provides
    fun rootDependency(): RootDependency =
        object : RootDependency {
            override fun rootDeps(): RootDeps = RootDeps()
        }

    @RootScope
    @Provides
    fun connector(): Connector =
        Connector()

    @RootScope
    @Provides
    fun screenFactoryDependencyResolver(rootDependency: RootDependency): ScreenFactoryDependencyProvider =
        ScreenFactoryDependencyProvider(rootDependency)

    @RootScope
    @Provides
    fun screenRouterFactoryProvider(screenFactoryDependencyProvider: ScreenFactoryDependencyProvider): com.example.router.ScreenRouterFactoryProvider =
        ProjectAScreenBuilderFactory(screenFactoryDependencyProvider)
}