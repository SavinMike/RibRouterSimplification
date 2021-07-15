package com.example.app.test_screen_e

import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.app.R
import com.example.app.test_screen_e.TestScreenEView.Event
import com.example.app.test_screen_e.TestScreenEView.ViewModel
import com.example.ribrouter.RouterDependency
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.screens.ScreenA
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenEView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency : RouterDependency {

        val localRouter: GlobalRouter<Screen>
    }

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenEView>
}

class TestScreenEViewImpl private constructor(
    override val androidView: ViewGroup,
    private val viewDependency: TestScreenEView.ViewDependency,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    TestScreenEView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_test_screen_e
    ) : TestScreenEView.Factory {

        override fun invoke(deps: TestScreenEView.ViewDependency): ViewFactory<TestScreenEView> = ViewFactory {
            TestScreenEViewImpl(
                it.inflate(layoutRes),
                deps
            )
        }
    }

    init {
        findViewById<Button>(R.id.screenE_global).setOnClickListener {
            viewDependency.globalRouter().addScreen(ScreenA("Global"))
        }
        findViewById<Button>(R.id.screenE_local).setOnClickListener {
            viewDependency.localRouter.addScreen(ScreenA("Local"))
        }
    }

    override fun accept(vm: ViewModel) {
    }

    override fun getParentViewForSubtree(subtreeOf: Node<*>): ViewGroup =
        findViewById(R.id.testScreenE_container)
}

