package com.example.testscreenb.screen_b

import android.util.Log
import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.example.common.RootDeps
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.screens.ProjectAScreen

class TestScreenBBuilder(
    private val dependency: TestScreenB.Dependency
) : SimpleBuilder<TestScreenB>() {

    init {
        Log.d("Init", "ScreenB.Builder")
    }

    override fun build(buildParams: BuildParams<Nothing?>): TestScreenB {
        val customisation = buildParams.getOrDefault(TestScreenB.Customisation())
        val viewDependency = viewDependency()

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
            rootDeps = dependency.rootDeps(),
        )
    }

    private fun viewDependency(): TestScreenBView.ViewDependency =
        object : TestScreenBView.ViewDependency {
            override fun globalRouter(): GlobalRouter<Screen> = dependency.globalRouter()
        }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        viewDependency: TestScreenBView.ViewDependency,
        customisation: TestScreenB.Customisation,
        rootDeps: RootDeps,
    ) = TestScreenBNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = emptyList(),
        rootDeps = rootDeps
    )
}
