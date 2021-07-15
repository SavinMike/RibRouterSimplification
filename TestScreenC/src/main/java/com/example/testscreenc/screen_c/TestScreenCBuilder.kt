package com.example.testscreenc.screen_c

import android.util.Log
import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.example.router.GlobalRouter
import com.example.router.Screen

class TestScreenCBuilder(
    private val dependency: TestScreenC.Dependency
) : SimpleBuilder<TestScreenC>() {

    init {
        Log.d("Init", "ScreenC.Builder")
    }

    override fun build(buildParams: BuildParams<Nothing?>): TestScreenC {
        val customisation = buildParams.getOrDefault(TestScreenC.Customisation())
        val viewDependency = viewDependency()

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
        )
    }

    private fun viewDependency(): TestScreenCView.ViewDependency =
        object : TestScreenCView.ViewDependency {
            override fun globalRoute(): GlobalRouter<Screen> = dependency.globalRouter()

            override fun screenProvider(): () -> Screen = { dependency.screen() }
        }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        viewDependency: TestScreenCView.ViewDependency,
        customisation: TestScreenC.Customisation,
    ) = TestScreenCNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = emptyList(),
    )
}
