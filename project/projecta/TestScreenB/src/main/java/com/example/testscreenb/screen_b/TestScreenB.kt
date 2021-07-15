package com.example.testscreenb.screen_b

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.common.RootDeps
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.screens.ProjectAScreen
import com.example.testscreenb.screen_b.TestScreenB.Input
import com.example.testscreenb.screen_b.TestScreenB.Output

interface TestScreenB : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency {
        fun rootDeps(): RootDeps

        fun globalRouter(): GlobalRouter<Screen>
    }

    interface RibDependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: TestScreenBView.Factory = TestScreenBViewImpl.Factory()
    ) : RibCustomisation
}
