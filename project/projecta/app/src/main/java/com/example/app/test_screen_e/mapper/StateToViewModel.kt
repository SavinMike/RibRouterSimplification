package com.example.app.test_screen_e.mapper

import com.example.app.test_screen_e.TestScreenEView.ViewModel
import com.example.app.test_screen_e.feature.TestScreenEFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        ViewModel()
}
