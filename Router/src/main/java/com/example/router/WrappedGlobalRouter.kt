package com.example.router

import android.util.Log

class WrappedGlobalRouter<S : Screen, MappedScreen : Screen>(
    val globalRouter: GlobalRouter<MappedScreen>,
    val mapper: (S) -> MappedScreen
) : GlobalRouter<S> {

    init {
        Log.d("Init", "WrappedGlobalRouter")
    }

    override fun root(screen: S) {
        globalRouter.root(mapper(screen))
    }

    override fun addScreen(screen: S) {
        globalRouter.addScreen(mapper(screen))
    }

    override fun replaceScreen(screen: S) {
        globalRouter.replaceScreen(mapper(screen))
    }

    override fun pop() {
        globalRouter.pop()
    }
}