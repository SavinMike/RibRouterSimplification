package com.example.app.screen

import com.badoo.ribs.core.modality.BuildContext
import com.example.ribrouter.ScreenRouterFactory
import com.example.router.Screen
import com.example.screens.ScreenA
import com.example.screens.ScreenC
import com.example.testscreenc.screen_c.TestScreenC
import com.example.testscreenc.screen_c.TestScreenCBuilder

class ScreenCRouterFactory(
    private val dependency: Dependency,
) : ScreenRouterFactory<ScreenC, TestScreenC> {

    interface Dependency : TestScreenC.RootDependency

    class AdapterDependency(dependency: Dependency) :
        TestScreenC.Dependency,
        TestScreenC.RootDependency by dependency {

        override fun screen(): Screen =
            ScreenA("Outer Dependency")
    }

    override fun invoke(buildContext: BuildContext, screen: ScreenC): TestScreenC =
        TestScreenCBuilder(AdapterDependency(dependency))
            .build(buildContext)
}