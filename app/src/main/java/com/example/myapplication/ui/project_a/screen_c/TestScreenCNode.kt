package com.example.myapplication.ui.project_a.screen_c

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.myapplication.ui.project_a.screen_c.TestScreenC.Input
import com.example.myapplication.ui.project_a.screen_c.TestScreenC.Output

class TestScreenCNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<TestScreenCView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<TestScreenCView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins,
), TestScreenC, Connectable<Input, Output> by connector
