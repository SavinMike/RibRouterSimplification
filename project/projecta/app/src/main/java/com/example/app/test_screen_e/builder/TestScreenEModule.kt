@file:SuppressWarnings("LongParameterList", "LongMethod")

package com.example.app.test_screen_e.builder

import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.rx2.disposables
import com.example.app.Connector
import com.example.app.test_screen_e.TestScreenE
import com.example.app.test_screen_e.TestScreenEInteractor
import com.example.app.test_screen_e.TestScreenENode
import com.example.app.test_screen_e.TestScreenEView
import com.example.app.test_screen_e.builder.TestScreenEBuilder.Params
import com.example.app.test_screen_e.feature.TestScreenEFeature
import com.example.app.test_screen_e.routing.TestScreenERouter
import com.example.app.test_screen_e.routing.TestScreenERouter.Configuration
import com.example.ribrouter.RouterDependency
import com.example.ribrouter.router.Router
import com.example.ribrouter.router.RouterBuilder
import com.example.router.GlobalRouter
import com.example.router.Screen
import dagger.Provides

@dagger.Module
internal object TestScreenEModule {

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun feature(): TestScreenEFeature =
        TestScreenEFeature()

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun backStack(
        buildParams: BuildParams<Params>
    ): BackStack<Configuration> =
        BackStack(
            buildParams = buildParams,
            initialConfiguration = Configuration.Default
        )

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun interactor(
        dependency: TestScreenE.Dependency,
        buildParams: BuildParams<Params>,
        backStack: BackStack<Configuration>,
        feature: TestScreenEFeature,
        @LocalRouter connector: Connector
    ): TestScreenEInteractor =
        TestScreenEInteractor(
            buildParams = buildParams,
            backStack = backStack,
            feature = feature,
            connector = connector,
        )

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun router(
        buildParams: BuildParams<Params>,
        backStack: BackStack<Configuration>,
        component: Router.Dependency,
        customisation: TestScreenE.Customisation
    ): TestScreenERouter =
        TestScreenERouter(
            buildParams = buildParams,
            routingSource = backStack,
            routerBuilder = RouterBuilder(component),
            transitionHandler = customisation.transitionHandler
        )

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun viewDependency(
        dependency: TestScreenE.Dependency,
        @LocalRouter localRouter: GlobalRouter<Screen>,
    ): TestScreenEView.ViewDependency =
        object : TestScreenEView.ViewDependency,
            RouterDependency by dependency {

            override val localRouter: GlobalRouter<Screen> = localRouter
        }

    @TestScreenEScope
    @Provides
    @JvmStatic
    internal fun node(
        buildParams: BuildParams<Params>,
        customisation: TestScreenE.Customisation,
        interactor: TestScreenEInteractor,
        router: TestScreenERouter,
        viewDependency: TestScreenEView.ViewDependency,
        feature: TestScreenEFeature,
    ): TestScreenENode = TestScreenENode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = listOf(
            interactor,
            router,
            disposables(feature)
        )
    )
}
