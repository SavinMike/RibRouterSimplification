package com.example.ribrouter

import android.util.Log
import com.example.ribrouter.router.Router
import com.example.router.GlobalRouter
import com.example.router.Screen
import io.reactivex.functions.Consumer

class RibGlobalRouter<S : Screen>(
    private val input: Consumer<Router.Input>,
) : GlobalRouter<S> {

    init {
        Log.d("Init", "RibGlobalRouter")
    }

    override fun addScreen(screen: S) {
        input.accept(Router.Input.AddScreen(screen))
    }

    override fun replaceScreen(screen: S) {
        input.accept(Router.Input.ReplaceScreen(screen))
    }

    override fun pop() {
        input.accept(Router.Input.Pop)
    }

    override fun root(screen: S) {
        input.accept(Router.Input.NewRoot(screen))
    }
}