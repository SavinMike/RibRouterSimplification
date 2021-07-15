package com.example.myapplication.ui.project_a.screen_b

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.myapplication.R
import com.example.myapplication.ui.project_a.screen_b.TestScreenBView.Event
import com.example.myapplication.ui.project_a.screen_b.TestScreenBView.ViewModel
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

    interface ViewDependency

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenBView>
}

class TestScreenBViewImpl private constructor(
    override val androidView: ViewGroup,
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
            )
        }
    }

    override fun accept(vm: ViewModel) {
    }
}
