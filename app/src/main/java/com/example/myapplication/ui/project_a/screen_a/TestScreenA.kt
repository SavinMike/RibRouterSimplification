package com.example.myapplication.ui.project_a.screen_a

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.myapplication.ui.project_a.screen_a.TestScreenA.Input
import com.example.myapplication.ui.project_a.screen_a.TestScreenA.Output

interface TestScreenA : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency

    interface RibDependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenAView.Factory = TestScreenAViewImpl.Factory()
    ) : RibCustomisation
}
