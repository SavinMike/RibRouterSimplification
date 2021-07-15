package com.example.fragmentrib.fragment_container

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.example.fragmentrib.R
import com.example.fragmentrib.fragment_container.FragmentContainerView.Event
import com.example.fragmentrib.fragment_container.FragmentContainerView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface FragmentContainerView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface ViewDependency {
        val params: FragmentContainerBuilder.Params

        val fragmentManager: FragmentManager
    }

    interface Factory : ViewFactoryBuilder<ViewDependency, FragmentContainerView>
}

class FragmentContainerViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    FragmentContainerView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_fragment_container
    ) : FragmentContainerView.Factory {
        override fun invoke(deps: FragmentContainerView.ViewDependency): ViewFactory<FragmentContainerView> =
            ViewFactory {
                FragmentContainerViewImpl(
                    it.inflate(layoutRes),
                )
            }
    }

    override fun accept(vm: ViewModel) {
    }
}
