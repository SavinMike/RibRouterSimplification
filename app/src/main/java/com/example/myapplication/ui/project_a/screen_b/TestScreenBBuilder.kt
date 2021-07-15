package com.example.myapplication.ui.project_a.screen_b

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.example.myapplication.ui.project_a.root.RootDeps

class TestScreenBBuilder(
    private val dependency: TestScreenB.Dependency
) : SimpleBuilder<TestScreenB>() {

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
        object : TestScreenBView.ViewDependency {}

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
