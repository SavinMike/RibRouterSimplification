package com.example.app.test_screen_e.mapper

import com.example.app.test_screen_e.TestScreenE.Output
import com.example.app.test_screen_e.feature.TestScreenEFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? =
        null
}
