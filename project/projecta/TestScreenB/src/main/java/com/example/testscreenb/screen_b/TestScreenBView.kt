package com.example.testscreenb.screen_b

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
import com.example.screens.ScreenA
import com.example.screens.ScreenC
import com.example.testscreenb.R
import com.example.testscreenb.screen_b.TestScreenBView.Event
import com.example.testscreenb.screen_b.TestScreenBView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenBView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency {
        fun globalRouter(): GlobalRouter<Screen>
    }

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenBView>
}

class TestScreenBViewImpl private constructor(
    override val androidView: ViewGroup,
    private val viewDependency: TestScreenBView.ViewDependency,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    TestScreenBView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_screen_b
    ) : TestScreenBView.Factory {
        override fun invoke(deps: TestScreenBView.ViewDependency): ViewFactory<TestScreenBView> = ViewFactory {
            TestScreenBViewImpl(
                it.inflate(layoutRes),
                deps
            )
        }
    }

    init {
        findViewById<Button>(R.id.screenA).setOnClickListener {
            viewDependency.globalRouter().addScreen(ScreenA("Test"))
        }
        findViewById<Button>(R.id.replaceScreenC).setOnClickListener {
            viewDependency.globalRouter().replaceScreen(ScreenC)
        }
    }

    override fun accept(vm: ViewModel) {
    }
}
