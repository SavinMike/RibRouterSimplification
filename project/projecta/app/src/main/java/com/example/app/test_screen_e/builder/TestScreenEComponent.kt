package com.example.app.test_screen_e.builder

import com.badoo.ribs.core.modality.BuildParams
import com.example.app.test_screen_e.TestScreenE
import com.example.app.test_screen_e.TestScreenENode
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.ribrouter.router.Router
import dagger.BindsInstance

@TestScreenEScope
@dagger.Component(
    modules = [
        TestScreenEModule::class,
        RouterModule::class,
    ],
    dependencies = [TestScreenE.Dependency::class]
)
internal interface TestScreenEComponent : Router.RootDependency {

    @dagger.Component.Factory
    interface Factory {
        fun create(
            dependency: TestScreenE.Dependency,
            @BindsInstance customisation: TestScreenE.Customisation,
            @BindsInstance buildParams: BuildParams<TestScreenEBuilder.Params>
        ): TestScreenEComponent
    }

    fun node(): TestScreenENode
}
