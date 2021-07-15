package com.example.myapplication.ui.project_a.screen_c

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.myapplication.ui.project_a.screen_c.TestScreenC.Input
import com.example.myapplication.ui.project_a.screen_c.TestScreenC.Output

interface TestScreenC : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency

    interface RibDependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenCView.Factory = TestScreenCViewImpl.Factory()
    ) : RibCustomisation
}
