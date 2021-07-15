@file:SuppressWarnings("LongParameterList", "LongMethod")

package com.example.app.test_screen_e.builder

import androidx.fragment.app.FragmentManager
import com.example.app.Connector
import com.example.app.di.RootDependency
import com.example.app.di.ScreenFactoryDependencyProvider
import com.example.app.screen.ProjectAScreenBuilderFactory
import com.example.ribrouter.RibGlobalRouter
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.ribrouter.router.Router
import com.example.router.GlobalRouter
import com.example.router.Screen
import dagger.Provides

@dagger.Module
internal object RouterModule {

    @TestScreenEScope
    @Provides
    @LocalRouter
    fun testScreenERouter(@LocalRouter connector: Connector): GlobalRouter<Screen> =
        RibGlobalRouter(connector.routerInput)

    @TestScreenEScope
    @Provides
    fun testScreenEScreenRouterFactoryProvider(
        @LocalRouter screenFactoryDependencyProvider: ScreenFactoryDependencyProvider,
        fragmentManager: FragmentManager,
    ): ScreenRouterFactoryProvider =
        ProjectAScreenBuilderFactory(screenFactoryDependencyProvider, fragmentManager)

    @TestScreenEScope
    @Provides
    @LocalRouter
    fun testScreenEConnector(): Connector =
        Connector()

    @TestScreenEScope
    @Provides
    @LocalRouter
    fun screenFactoryDependencyProvider(
        @LocalRouter router: GlobalRouter<Screen>,
        rootDependency: RootDependency,
    ): ScreenFactoryDependencyProvider =
        ScreenFactoryDependencyProvider(
            object : RootDependency by rootDependency {
                override fun globalRouter(): GlobalRouter<Screen> = router
            }
        )

    @TestScreenEScope
    @Provides
    fun routerDependency(
        component: TestScreenEComponent,
        screenRouterFactoryProvider: ScreenRouterFactoryProvider
    ): Router.Dependency =
        object : Router.Dependency,
            Router.RootDependency by component {
            override fun screenRouterFactoryProvider(): ScreenRouterFactoryProvider =
                screenRouterFactoryProvider
        }
}
