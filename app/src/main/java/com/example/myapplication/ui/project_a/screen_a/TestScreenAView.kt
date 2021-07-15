package com.example.myapplication.ui.project_a.screen_a

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.myapplication.R
import com.example.myapplication.ui.project_a.screen_a.TestScreenAView.Event
import com.example.myapplication.ui.project_a.screen_a.TestScreenAView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TestScreenAView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency

    interface Factory : ViewFactoryBuilder<ViewDependency, TestScreenAView>
}

class TestScreenAViewImpl private constructor(
    override val androidView: ViewGroup,
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
                it.inflate(layoutRes)
            )
        }
    }

    override fun accept(vm: ViewModel) {
    }
}
