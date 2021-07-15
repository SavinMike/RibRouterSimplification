package com.example.testscreenc.screen_c

import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.testscreenc.R
import com.example.testscreenc.screen_c.TestScreenCView.Event
import com.example.testscreenc.screen_c.TestScreenCView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenCView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency {
        fun globalRoute(): GlobalRouter<Screen>

        fun screenProvider(): () -> Screen
    }

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenCView>
}

class TestScreenCViewImpl private constructor(
    override val androidView: ViewGroup,
    private val deps: TestScreenCView.ViewDependency,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    TestScreenCView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_screen_c
    ) : TestScreenCView.Factory {
        override fun invoke(deps: TestScreenCView.ViewDependency): ViewFactory<TestScreenCView> = ViewFactory {
            TestScreenCViewImpl(
                it.inflate(layoutRes),
                deps
            )
        }
    }

    init {
        findViewById<Button>(R.id.screenA).setOnClickListener {
            deps.globalRoute().addScreen(screen = deps.screenProvider().invoke())
        }
    }

    override fun accept(vm: ViewModel) {
    }
}
