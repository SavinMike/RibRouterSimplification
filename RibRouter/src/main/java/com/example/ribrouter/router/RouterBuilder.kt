@file:SuppressWarnings("LongParameterList")

package com.example.ribrouter.router

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.source.backstack.BackStack
import com.example.ribrouter.router.routing.RouterRouter
import com.example.ribrouter.router.routing.RouterRouter.Configuration
import com.example.ribrouter.ScreenRouterFactoryProvider

class RouterBuilder(
    private val dependency: Router.Dependency
) : SimpleBuilder<Router>() {

    override fun build(buildParams: BuildParams<Nothing?>): Router {
        val customisation = buildParams.getOrDefault(Router.Customisation())
        val backStack = backStack(buildParams)
        val interactor: RouterInteractor =
            interactor(
                buildParams = buildParams,
                backStack = backStack,
            )
        val router: RouterRouter =
            router(
                buildParams = buildParams,
                routingSource = backStack,
                screenRouterFactoryProvider = dependency.screenRouterFactoryProvider(),
                customisation = customisation,
            )

        return node(
            buildParams = buildParams,
            interactor = interactor,
            router = router,
        )
    }

    private fun backStack(buildParams: BuildParams<*>) =
        BackStack<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.Noop
        )

    private fun interactor(
        buildParams: BuildParams<*>,
        backStack: BackStack<Configuration>,
    ) = RouterInteractor(
        buildParams = buildParams,
        backStack = backStack,
    )

    private fun router(
        buildParams: BuildParams<*>,
        routingSource: RoutingSource<Configuration>,
        customisation: Router.Customisation,
        screenRouterFactoryProvider: ScreenRouterFactoryProvider
    ) = RouterRouter(
        buildParams = buildParams,
        routingSource = routingSource,
        screenRouterFactoryProvider = screenRouterFactoryProvider,
        transitionHandler = customisation.transitionHandler
    )

    private fun node(
        buildParams: BuildParams<*>,
        interactor: RouterInteractor,
        router: RouterRouter
    ) = RouterNode(
        buildParams = buildParams,
        plugins = listOf(
            interactor,
            router,
        )
    )
}
