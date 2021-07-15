package com.example.fragmentrib.fragment_container

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.ViewAware
import com.badoo.ribs.core.plugin.ViewLifecycleAware
import com.example.fragmentrib.R
import java.lang.ref.WeakReference

class FragmentAttachPlugin(
    private val viewDependency: FragmentContainerView.ViewDependency,
    private val buildParams: BuildParams<*>,
) : ViewAware<FragmentContainerView>,
    ViewLifecycleAware {

    private var view = WeakReference<FragmentContainerView>(null)

    override fun onViewCreated(view: FragmentContainerView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        this.view = WeakReference(view)
    }

    override fun onAttachToView() {
        val fragment =
            viewDependency
                .fragmentManager
                .findFragmentById(R.id.rib_fragment_container)

        val tag =
            buildParams.identifier.uuid.toString() + viewDependency.params.fragmentClass.name

        if (fragment != null) {
            viewDependency
                .fragmentManager
                .beginTransaction()
                .attach(fragment)
                .commitNowAllowingStateLoss()
        } else {
            viewDependency.fragmentManager
                .beginTransaction()
                .replace(
                    R.id.rib_fragment_container,
                    viewDependency.params.fragmentClass,
                    viewDependency.params.arguments,
                    tag,
                )
                .commitNowAllowingStateLoss()
        }
    }

    override fun onDetachFromView() {
        val tag =
            buildParams.identifier.uuid.toString() + viewDependency.params.fragmentClass.name
        val fragment =
            viewDependency
                .fragmentManager
                .findFragmentByTag(tag)
        fragment?.let {
            viewDependency.fragmentManager
                .beginTransaction()
                .detach(fragment)
                .commitAllowingStateLoss()
        }
    }
}