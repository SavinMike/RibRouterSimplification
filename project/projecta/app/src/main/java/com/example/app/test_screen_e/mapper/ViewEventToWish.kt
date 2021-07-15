package com.example.app.test_screen_e.mapper

import com.example.app.test_screen_e.TestScreenEView.Event
import com.example.app.test_screen_e.feature.TestScreenEFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        null
}
