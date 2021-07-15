package com.example.myapplication.ui.project_a.screen_c

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams

class TestScreenCBuilder(
    private val dependency: TestScreenC.Dependency
) : SimpleBuilder<TestScreenC>() {

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
        object : TestScreenCView.ViewDependency {}

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
