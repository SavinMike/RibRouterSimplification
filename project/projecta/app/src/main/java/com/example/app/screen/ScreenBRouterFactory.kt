package com.example.app.screen

import com.badoo.ribs.core.modality.BuildContext
import com.example.ribrouter.ScreenRouterFactory
import com.example.screens.ScreenB
import com.example.testscreenb.screen_b.TestScreenB
import com.example.testscreenb.screen_b.TestScreenBBuilder

class ScreenBRouterFactory(
    private val dependency: Dependency,
) : ScreenRouterFactory<ScreenB, TestScreenB> {

    interface Dependency : TestScreenB.RootDependency

    class AdapterDependency(dependency: Dependency) :
        TestScreenB.Dependency,
        TestScreenB.RootDependency by dependency

    override fun invoke(buildContext: BuildContext, screen: ScreenB): TestScreenB =
        TestScreenBBuilder(AdapterDependency(dependency))
            .build(buildContext)
}