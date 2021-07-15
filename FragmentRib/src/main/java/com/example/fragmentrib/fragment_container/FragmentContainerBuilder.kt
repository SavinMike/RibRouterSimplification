package com.example.fragmentrib.fragment_container

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.store.RetainedInstanceStore

class FragmentContainerBuilder(
    private val dependency: FragmentContainer.Dependency
) : Builder<FragmentContainerBuilder.Params, FragmentContainer>() {

    data class Params(
        val fragmentClass: Class<out Fragment>,
        val arguments: Bundle? = null,
    )

    override fun build(buildParams: BuildParams<Params>): FragmentContainer {
        val customisation = buildParams.getOrDefault(FragmentContainer.Customisation())
        val viewDependency = viewDependency(buildParams)
        val fragmentAttachPlugin =
            RetainedInstanceStore.get(
                owner = buildParams.identifier,
                factory = { FragmentAttachPlugin(viewDependency, buildParams) },
                disposer = {},
                clazz = FragmentAttachPlugin::class,
            )

        return node(
            buildParams = buildParams,
            viewDependency = viewDependency,
            customisation = customisation,
            fragmentAttachPlugin = fragmentAttachPlugin,
        )
    }

    private fun viewDependency(
        buildParams: BuildParams<Params>,
    ): FragmentContainerView.ViewDependency =
        object : FragmentContainerView.ViewDependency {
            override val params: Params = buildParams.payload

            override val fragmentManager: FragmentManager = dependency.fragmentManager
        }

    private fun node(
        buildParams: BuildParams<Params>,
        viewDependency: FragmentContainerView.ViewDependency,
        customisation: FragmentContainer.Customisation,
        fragmentAttachPlugin: FragmentAttachPlugin,
    ) = FragmentContainerNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(viewDependency),
        plugins = listOf(fragmentAttachPlugin),
    )
}
