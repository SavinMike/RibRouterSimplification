package com.example.myapplication.ui.project_a.screen_b

import com.badoo.ribs.clienthelper.connector.Connectable
import com.badoo.ribs.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.myapplication.ui.project_a.root.RootDeps
import com.example.myapplication.ui.project_a.screen_b.TestScreenB.Input
import com.example.myapplication.ui.project_a.screen_b.TestScreenB.Output

class TestScreenBNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<TestScreenBView>?,
    plugins: List<Plugin> = emptyList(),
    rootDeps: RootDeps,
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<TestScreenBView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins,
), TestScreenB, Connectable<Input, Output> by connector
