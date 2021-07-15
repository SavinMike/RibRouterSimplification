package com.example.testscreena.screen_a

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.router.GlobalRouter
import com.example.router.Screen
import com.example.testscreena.R
import com.example.testscreena.screen_a.TestScreenAView.Event
import com.example.testscreena.screen_a.TestScreenAView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenAView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val text: String,
    )

    interface ViewDependency {
        val params: String

        val globalRouter: GlobalRouter<Screen>
    }

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenAView>
}

class TestScreenAViewImpl private constructor(
    override val androidView: ViewGroup,
    private val viewDependency: TestScreenAView.ViewDependency,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    TestScreenAView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_screen_a
    ) : TestScreenAView.Factory {
        override fun invoke(deps: TestScreenAView.ViewDependency): ViewFactory<TestScreenAView> = ViewFactory {
            TestScreenAViewImpl(
                it.inflate(layoutRes),
                deps
            )
        }
    }

    init {
        findViewById<TextView>(R.id.screenA_text).text = "Screen A\n${viewDependency.params}"
        findViewById<Button>(R.id.screenA_back).setOnClickListener { viewDependency.globalRouter.pop() }
    }

    override fun accept(vm: ViewModel) {
    }
}
