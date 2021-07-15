package com.example.app.di

import androidx.fragment.app.FragmentManager
import com.example.app.Connector
import com.example.app.screen.ProjectAScreenBuilderFactory
import com.example.ribrouter.RibGlobalRouter
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.router.GlobalRouter
import com.example.screens.ProjectAScreen
import dagger.Module
import dagger.Provides

@Module
object RouterModule {

    @RootScope
    @Provides
    fun globalRouter(connector: Connector): GlobalRouter<ProjectAScreen> =
        RibGlobalRouter(connector.routerInput)

    @RootScope
    @Provides
    fun screenRouterFactoryProvider(
        screenFactoryDependencyProvider: ScreenFactoryDependencyProvider,
        fragmentManager: FragmentManager,
    ): ScreenRouterFactoryProvider =
        ProjectAScreenBuilderFactory(screenFactoryDependencyProvider, fragmentManager)

    @RootScope
    @Provides
    fun connector(): Connector =
        Connector()
}