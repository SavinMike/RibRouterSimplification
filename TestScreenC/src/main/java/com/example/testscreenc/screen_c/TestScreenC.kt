package com.example.testscreenc.screen_c

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.testscreenc.screen_c.TestScreenC.Input
import com.example.testscreenc.screen_c.TestScreenC.Output

interface TestScreenC : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency {
        fun globalRouter(): GlobalRouter<Screen>
    }

    interface RibDependency {
        fun screen(): Screen
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenCView.Factory = TestScreenCViewImpl.Factory()
    ) : RibCustomisation
}
