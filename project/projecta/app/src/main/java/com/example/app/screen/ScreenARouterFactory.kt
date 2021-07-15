package com.example.app.screen

import com.badoo.ribs.core.modality.BuildContext
import com.example.ribrouter.ScreenRouterFactory
import com.example.screens.ScreenA
import com.example.testscreena.screen_a.TestScreenA
import com.example.testscreena.screen_a.TestScreenABuilder

class ScreenARouterFactory(
    private val dependency: Dependency,
) : ScreenRouterFactory<ScreenA, TestScreenA> {

    interface Dependency : TestScreenA.RootDependency

    class AdapterDependency(dependency: Dependency) :
        TestScreenA.Dependency,
        TestScreenA.RootDependency by dependency

    override fun invoke(buildContext: BuildContext, screen: ScreenA): TestScreenA =
        TestScreenABuilder(AdapterDependency(dependency))
            .build(buildContext, TestScreenABuilder.Params(screen.test))
}