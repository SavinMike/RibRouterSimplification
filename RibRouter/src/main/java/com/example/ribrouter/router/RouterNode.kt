package com.example.ribrouter.router

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.example.ribrouter.router.Router.Input
import com.example.ribrouter.router.Router.Output

class RouterNode internal constructor(
    buildParams: BuildParams<*>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector(),
) : Node<Nothing>(
    buildParams = buildParams,
    viewFactory = null,
    plugins = plugins,
), Router, Connectable<Input, Output> by connector
