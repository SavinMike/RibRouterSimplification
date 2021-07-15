package com.example.app.test_screen_d

import android.util.Log
import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.example.ribrouter.RouterDependency

class TestScreenDBuilder(
    private val dependency: TestScreenD.Dependency
) : SimpleBuilder<TestScreenD>() {

    init {
        Log.d("Init", "TestScreenD.Builder")
    }

    override fun build(buildParams: BuildParams<Nothing?>): TestScreenD {
        val customisation = buildParams.getOrDefault(TestScreenD.Customisation())
        val viewDependency = viewDependency()

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
        )
    }

    private fun viewDependency(): TestScreenDView.ViewDependency =
        object : TestScreenDView.ViewDependency,
            RouterDependency by dependency {}

    private fun node(
        buildParams: BuildParams<Nothing?>,
        viewDependency: TestScreenDView.ViewDependency,
        customisation: TestScreenD.Customisation,
    ) = TestScreenDNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = emptyList(),
    )
}
