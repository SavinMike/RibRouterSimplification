package com.example.fragmentrib.fragment_container

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.example.fragmentrib.fragment_container.FragmentContainer.Input
import com.example.fragmentrib.fragment_container.FragmentContainer.Output

class FragmentContainerNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<FragmentContainerView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<FragmentContainerView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins,
), FragmentContainer, Connectable<Input, Output> by connector{


}
