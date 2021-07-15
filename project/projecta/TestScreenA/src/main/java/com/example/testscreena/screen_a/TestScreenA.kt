package com.example.testscreena.screen_a

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.testscreena.screen_a.TestScreenA.Input
import com.example.testscreena.screen_a.TestScreenA.Output

interface TestScreenA : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency {
        fun globalRouter(): GlobalRouter<Screen>
    }

    interface RibDependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenAView.Factory = TestScreenAViewImpl.Factory()
    ) : RibCustomisation
}
