package com.example.testscreena.screen_a

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.testscreena.screen_a.TestScreenA.Input
import com.example.testscreena.screen_a.TestScreenA.Output

class TestScreenANode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<TestScreenAView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<TestScreenAView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins,
), TestScreenA, Connectable<Input, Output> by connector
