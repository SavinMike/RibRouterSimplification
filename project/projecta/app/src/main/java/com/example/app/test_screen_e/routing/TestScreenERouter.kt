package com.example.app.test_screen_e.routing

import android.os.Parcelable
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.app.test_screen_e.routing.TestScreenERouter.Configuration
import com.example.ribrouter.router.RouterBuilder
import kotlinx.parcelize.Parcelize

class TestScreenERouter internal constructor(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val routerBuilder: RouterBuilder,
    transitionHandler: TransitionHandler<Configuration>? = null
) : Router<Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {
    sealed class Configuration : Parcelable {
        @Parcelize
        object Default : Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): Resolution =
        when (routing.configuration) {
            is Configuration.Default ->
                child { routerBuilder.build(it) }
        }
}


