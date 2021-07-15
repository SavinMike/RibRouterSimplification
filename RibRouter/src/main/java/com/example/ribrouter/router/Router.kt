package com.example.ribrouter.router

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.example.ribrouter.router.Router.Input
import com.example.ribrouter.router.Router.Output
import com.example.ribrouter.router.routing.RouterRouter
import com.example.ribrouter.ScreenRouterFactoryProvider

interface Router : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency

    interface RibDependency {
        fun screenRouterFactoryProvider(): ScreenRouterFactoryProvider
    }

    sealed class Input {
        data class ReplaceScreen(val screen: com.example.router.Screen) : Input()

        data class AddScreen(val screen: com.example.router.Screen) : Input()

        object Pop : Input()

        data class NewRoot(val screen: com.example.router.Screen): Input()
    }

    sealed class Output {
        object ReadyForUse: Output()
    }

    class Customisation(
        val transitionHandler: TransitionHandler<RouterRouter.Configuration>? = null
    ) : RibCustomisation
}
