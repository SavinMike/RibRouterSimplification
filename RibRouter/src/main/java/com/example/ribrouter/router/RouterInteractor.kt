package com.example.ribrouter.router

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.android.subscribe
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.newRoot
import com.badoo.ribs.routing.source.backstack.operation.pop
import com.badoo.ribs.routing.source.backstack.operation.push
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.example.ribrouter.router.routing.RouterRouter.Configuration
import io.reactivex.functions.Consumer

internal class RouterInteractor(
    buildParams: BuildParams<*>,
    private val backStack: BackStack<Configuration>,
) : Interactor<Router, Nothing>(
    buildParams = buildParams
) {

    private val routerConsumer =
        Consumer<Router.Input> {
            when (it) {
                is Router.Input.AddScreen ->
                    backStack.push(Configuration.ScreenContent(it.screen))
                is Router.Input.ReplaceScreen ->
                    backStack.replace(Configuration.ScreenContent(it.screen))
                is Router.Input.Pop ->
                    backStack.pop()
                is Router.Input.NewRoot ->
                    backStack.newRoot(Configuration.ScreenContent(it.screen))
            }
        }

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            bind(rib.input to routerConsumer)
        }

        nodeLifecycle.subscribe(
            onCreate = {
                rib.output.accept(Router.Output.ReadyForUse)
            }
        )
    }

    override fun onChildBuilt(child: Node<*>) {
    }
}
