package com.example.ribrouter.router.routing

import android.os.Parcelable
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.ribrouter.router.routing.RouterRouter.Configuration
import com.example.ribrouter.ScreenRouterFactoryProvider
import kotlinx.parcelize.Parcelize

class RouterRouter internal constructor(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val screenRouterFactoryProvider: ScreenRouterFactoryProvider,
    transitionHandler: TransitionHandler<Configuration>? = null
) : Router<Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {
    sealed class Configuration : Parcelable {
        @Parcelize
        object Noop : Configuration()

        @Parcelize
        data class ScreenContent(val screen: com.example.router.Screen) : Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): Resolution =
        when (val configuration = routing.configuration) {
            is Configuration.ScreenContent ->
                child { screenRouterFactoryProvider.resolve(it, configuration.screen) }
            is Configuration.Noop -> Resolution.noop()
        }
}

