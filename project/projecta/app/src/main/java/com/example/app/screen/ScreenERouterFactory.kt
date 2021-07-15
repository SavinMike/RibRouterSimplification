package com.example.app.screen

import androidx.fragment.app.FragmentManager
import com.badoo.ribs.core.modality.BuildContext
import com.example.app.di.RootDependency
import com.example.app.test_screen_e.TestScreenE
import com.example.app.test_screen_e.builder.TestScreenEBuilder
import com.example.ribrouter.ScreenRouterFactory
import com.example.screens.ScreenE

class ScreenERouterFactory(
    private val dependency: Dependency,
    private val rootDependency: RootDependency,
    private val fragmentManager: FragmentManager,
) : ScreenRouterFactory<ScreenE, TestScreenE> {

    interface Dependency : TestScreenE.RootDependency

    class AdapterDependency(
        dependency: Dependency,
        override val rootDependency: RootDependency,
        override val fragmentManager: FragmentManager,
    ) : TestScreenE.Dependency,
        TestScreenE.RootDependency by dependency

    override fun invoke(buildContext: BuildContext, screen: ScreenE): TestScreenE =
        TestScreenEBuilder(AdapterDependency(dependency, rootDependency, fragmentManager))
            .build(buildContext, TestScreenEBuilder.Params(1))
}