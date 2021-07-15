package com.example.app.test_screen_e

import androidx.fragment.app.FragmentManager
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.example.app.test_screen_e.TestScreenE.Input
import com.example.app.test_screen_e.TestScreenE.Output
import com.example.app.test_screen_e.routing.TestScreenERouter
import com.example.ribrouter.RouterDependency
import com.example.ribrouter.router.Router

interface TestScreenE : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency : Router.RootDependency, RouterDependency

    interface RibDependency {
        val fragmentManager: FragmentManager
        val rootDependency: com.example.app.di.RootDependency
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenEView.Factory = TestScreenEViewImpl.Factory(),
        val transitionHandler: TransitionHandler<TestScreenERouter.Configuration>? = null
    ) : RibCustomisation
}
