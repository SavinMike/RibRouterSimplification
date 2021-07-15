package com.example.testscreena.screen_a

import android.util.Log
import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.example.router.GlobalRouter
import com.example.router.Screen

class TestScreenABuilder(
    private val dependency: TestScreenA.Dependency
) : Builder<TestScreenABuilder.Params, TestScreenA>() {

    init {
        Log.d("Init", "ScreenA.Builder")
    }

    data class Params(
        val string: String
    )

    override fun build(buildParams: BuildParams<Params>): TestScreenA {
        val customisation = buildParams.getOrDefault(TestScreenA.Customisation())
        val viewDependency = viewDependency(buildParams)

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
        )
    }

    private fun viewDependency(buildParams: BuildParams<Params>): TestScreenAView.ViewDependency =
        object : TestScreenAView.ViewDependency {
            override val params: String = buildParams.payload.string
            override val globalRouter: GlobalRouter<Screen> = dependency.globalRouter()
        }

    private fun node(
        buildParams: BuildParams<Params>,
        viewDependency: TestScreenAView.ViewDependency,
        customisation: TestScreenA.Customisation,
    ) = TestScreenANode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = emptyList(),
    )
}
