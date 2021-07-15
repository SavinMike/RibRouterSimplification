package com.example.app.test_screen_e

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.example.app.test_screen_e.TestScreenE.Input
import com.example.app.test_screen_e.TestScreenE.Output

class TestScreenENode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<TestScreenEView>?,
    plugins: List<Plugin>,
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<TestScreenEView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), TestScreenE, Connectable<Input, Output> by connector
