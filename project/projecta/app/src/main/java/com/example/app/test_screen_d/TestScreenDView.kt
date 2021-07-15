package com.example.app.test_screen_d

import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.app.R
import com.example.app.test_screen_d.TestScreenDView.Event
import com.example.app.test_screen_d.TestScreenDView.ViewModel
import com.example.ribrouter.RouterDependency
import com.example.screens.ScreenB
import com.example.screens.ScreenE
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenDView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency : RouterDependency

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenDView>
}

class TestScreenDViewImpl private constructor(
    override val androidView: ViewGroup,
    viewDependency: TestScreenDView.ViewDependency,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    TestScreenDView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_test_screen_d
    ) : TestScreenDView.Factory {
        override fun invoke(deps: TestScreenDView.ViewDependency): ViewFactory<TestScreenDView> = ViewFactory {
            TestScreenDViewImpl(
                it.inflate(layoutRes),
                deps
            )
        }
    }

    init {
        findViewById<Button>(R.id.screenD_openScreenB).setOnClickListener {
            viewDependency.globalRouter().addScreen(ScreenB)
        }

        findViewById<Button>(R.id.screenD_openScreenE).setOnClickListener {
            viewDependency.globalRouter().addScreen(ScreenE)
        }
    }

    override fun accept(vm: ViewModel) {
    }
}
