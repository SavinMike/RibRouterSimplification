package com.example.app.test_screen_d

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.app.test_screen_d.TestScreenD.Input
import com.example.app.test_screen_d.TestScreenD.Output

class TestScreenDNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<TestScreenDView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<TestScreenDView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins,
), TestScreenD, Connectable<Input, Output> by connector
