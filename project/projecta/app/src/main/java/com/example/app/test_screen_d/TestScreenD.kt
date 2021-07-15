package com.example.app.test_screen_d

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.app.test_screen_d.TestScreenD.Input
import com.example.app.test_screen_d.TestScreenD.Output
import com.example.ribrouter.RouterDependency

interface TestScreenD : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency : RouterDependency

    interface RibDependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenDView.Factory = TestScreenDViewImpl.Factory()
    ) : RibCustomisation
}
