package com.example.myapplication.ui.project_a.screens

import com.badoo.ribs.core.modality.BuildContext
import com.example.myapplication.ui.project_a.screen_a.TestScreenA
import com.example.myapplication.ui.project_a.screen_a.TestScreenABuilder
import com.example.router.ScreenRouterFactory
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenA(val test: String) : ProjectAScreen()

class ScreenARouterFactory(
    val dependency: Dependency,
) : com.example.router.ScreenRouterFactory<ScreenA, TestScreenA> {

    interface Dependency : TestScreenA.RootDependency

    inner class AdapterDependency :
        TestScreenA.Dependency,
        TestScreenA.RootDependency by dependency

    private val builder: TestScreenABuilder = TestScreenABuilder(AdapterDependency())

    override fun invoke(buildContext: BuildContext, screen: ScreenA): TestScreenA =
        builder.build(buildContext, TestScreenABuilder.Params(screen.test))
}