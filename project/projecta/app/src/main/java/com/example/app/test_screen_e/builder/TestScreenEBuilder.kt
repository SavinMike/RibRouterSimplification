package com.example.app.test_screen_e.builder

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.example.app.test_screen_e.TestScreenE
import com.example.app.test_screen_e.builder.TestScreenEBuilder.Params

class TestScreenEBuilder(
    private val dependency: TestScreenE.Dependency
) : Builder<Params, TestScreenE>() {

    data class Params(
        val someField: Int
    )

    override fun build(buildParams: BuildParams<Params>): TestScreenE =
        DaggerTestScreenEComponent
            .factory()
            .create(
                dependency = dependency,
                customisation = buildParams.getOrDefault(TestScreenE.Customisation()),
                buildParams = buildParams
            )
            .node()
}
