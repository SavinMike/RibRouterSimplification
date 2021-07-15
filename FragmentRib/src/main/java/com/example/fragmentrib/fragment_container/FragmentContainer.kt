package com.example.fragmentrib.fragment_container

import androidx.fragment.app.FragmentManager
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.example.fragmentrib.fragment_container.FragmentContainer.Input
import com.example.fragmentrib.fragment_container.FragmentContainer.Output

interface FragmentContainer : Rib, Connectable<Input, Output> {

    interface Dependency : RootDependency, RibDependency

    interface RootDependency

    interface RibDependency {
        val fragmentManager: FragmentManager
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: FragmentContainerView.Factory = FragmentContainerViewImpl.Factory()
    ) : RibCustomisation
}
