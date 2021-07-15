package com.example.app.test_screen_e

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.example.app.Connector
import com.example.app.test_screen_e.feature.TestScreenEFeature
import com.example.app.test_screen_e.mapper.InputToWish
import com.example.app.test_screen_e.mapper.NewsToOutput
import com.example.app.test_screen_e.mapper.StateToViewModel
import com.example.app.test_screen_e.mapper.ViewEventToWish
import com.example.app.test_screen_e.routing.TestScreenERouter.Configuration
import com.example.ribrouter.router.Router

internal class TestScreenEInteractor(
    buildParams: BuildParams<*>,
    private val feature: TestScreenEFeature,
    private val connector: Connector,
    private val backStack: BackStack<Configuration>
) : Interactor<TestScreenE, TestScreenEView>(
    buildParams = buildParams,
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(feature.news to rib.output using NewsToOutput)
            bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: TestScreenEView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
            bind(view to feature using ViewEventToWish)
        }
    }

    override fun onChildBuilt(child: Node<*>) {
        super.onChildBuilt(child)
        child.lifecycle.createDestroy {
            when (child) {
                is Router -> {
                    bind(connector.routerInput to child.input)
                    bind(child.output to connector.routerOutput)
                }
            }
        }
    }
}
