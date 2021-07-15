package com.example.app.di

import com.example.router.GlobalRouter
import com.example.router.ProjectAGlobalRouter
import com.example.myapplication.ui.project_a.screens.ProjectAScreen
import dagger.Module
import dagger.Provides

@Module
object RouterModule {

    @RootScope
    @Provides
    fun globalRouter(connector: Connector): com.example.router.GlobalRouter<ProjectAScreen> =
        com.example.router.ProjectAGlobalRouter(connector.routerInput)
}