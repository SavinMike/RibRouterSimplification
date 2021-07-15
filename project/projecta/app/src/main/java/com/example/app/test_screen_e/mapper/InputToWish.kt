package com.example.app.test_screen_e.mapper

import com.example.app.test_screen_e.TestScreenE.Input
import com.example.app.test_screen_e.feature.TestScreenEFeature.Wish

internal object InputToWish : (Input) -> Wish? {

    override fun invoke(event: Input): Wish? =
        null
}
