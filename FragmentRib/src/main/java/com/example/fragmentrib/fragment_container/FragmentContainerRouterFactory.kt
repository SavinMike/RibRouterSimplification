package com.example.fragmentrib.fragment_container

import androidx.fragment.app.FragmentManager
import com.badoo.ribs.core.modality.BuildContext
import com.example.ribrouter.ScreenRouterFactory
import com.example.screens.FragmentScreen

class FragmentContainerRouterFactory(
    private val dependency: Dependency,
    private val fragmentManager: FragmentManager,
) : ScreenRouterFactory<FragmentScreen, FragmentContainer> {

    interface Dependency : FragmentContainer.RootDependency

    class AdapterDependency(
        dependency: Dependency,
        override val fragmentManager: FragmentManager,
    ) : FragmentContainer.Dependency,
        FragmentContainer.RootDependency by dependency

    override fun invoke(buildContext: BuildContext, screen: FragmentScreen): FragmentContainer =
        FragmentContainerBuilder(AdapterDependency(dependency, fragmentManager))
            .build(buildContext, FragmentContainerBuilder.Params(screen.fragmentClass, screen.arguments))
}