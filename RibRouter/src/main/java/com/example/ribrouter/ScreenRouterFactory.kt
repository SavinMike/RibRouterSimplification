package com.example.ribrouter

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.example.router.Screen

interface ScreenRouterFactory<S : Screen, R : Rib> {

    operator fun invoke(buildContext: BuildContext, screen: S): R
}