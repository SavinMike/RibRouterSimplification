package com.example.ribrouter

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.example.router.Screen

open class ScreenRouterFactoryProvider(block: ScreenRouterFactoryProvider.() -> Unit) {

    private val map = mutableMapOf<Class<*>, Lazy<ScreenRouterFactory<*, *>>>()

    init {
        block()
    }

    inline fun <reified S : Screen, F : ScreenRouterFactory<S, *>> addScreenRouterFactory(factory: Lazy<F>) {
        addScreenRouterFactory(S::class.java, factory)
    }

    fun <S : Screen, F : ScreenRouterFactory<S, *>> addScreenRouterFactory(screenClass: Class<S>, factory: Lazy<F>) {
        map[screenClass] = factory
    }

    infix fun <S : Screen> Class<S>.resolveBy(factory: Lazy<ScreenRouterFactory<S, *>>) {
        addScreenRouterFactory(this, factory)
    }

    inline fun <reified S : Screen> resolve(buildContext: BuildContext, screen: S): Rib =
        resolve(buildContext, screen, screen::class.java)

    fun <S : Screen> resolve(buildContext: BuildContext, screen: S, screenClass: Class<out S>): Rib =
        (map[screenClass] as? Lazy<ScreenRouterFactory<S, *>>)?.value?.invoke(
            buildContext,
            screen
        ) ?: throw IllegalArgumentException("Unknown how to resolve screen: $screen")
}