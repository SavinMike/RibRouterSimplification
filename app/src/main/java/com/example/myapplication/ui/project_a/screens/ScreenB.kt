package com.example.myapplication.ui.project_a.screens

import com.badoo.ribs.core.modality.BuildContext
import com.example.myapplication.ui.project_a.screen_b.TestScreenB
import com.example.myapplication.ui.project_a.screen_b.TestScreenBBuilder
import com.example.router.ScreenRouterFactory
import kotlinx.parcelize.Parcelize

@Parcelize
object ScreenB : ProjectAScreen()

class ScreenBRouterFactory(
    val dependency: Dependency,
) : com.example.router.ScreenRouterFactory<ScreenB, TestScreenB> {

    interface Dependency : TestScreenB.RootDependency

    inner class AdapterDependency :
        TestScreenB.Dependency,
        TestScreenB.RootDependency by dependency

    private val builder = TestScreenBBuilder(AdapterDependency())

    override fun invoke(buildContext: BuildContext, screen: ScreenB): TestScreenB =
        builder.build(buildContext)
}