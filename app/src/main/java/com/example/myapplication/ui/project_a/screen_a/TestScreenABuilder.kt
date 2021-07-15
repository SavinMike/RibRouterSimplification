package com.example.myapplication.ui.project_a.screen_a

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams

class TestScreenABuilder(
    private val dependency: TestScreenA.Dependency
) : Builder<TestScreenABuilder.Params, TestScreenA>() {

    data class Params(
        val string: String
    )

    override fun build(buildParams: BuildParams<Params>): TestScreenA {
        val customisation = buildParams.getOrDefault(TestScreenA.Customisation())
        val viewDependency = viewDependency()

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
        )
    }

    private fun viewDependency(): TestScreenAView.ViewDependency =
        object : TestScreenAView.ViewDependency {}

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
