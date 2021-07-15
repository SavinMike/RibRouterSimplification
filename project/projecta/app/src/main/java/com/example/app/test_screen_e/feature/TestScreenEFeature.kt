package com.example.app.test_screen_e.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.mvicore.feature.BaseFeature
import com.example.app.test_screen_e.feature.TestScreenEFeature.Action.ExecuteWish
import com.example.app.test_screen_e.feature.TestScreenEFeature.Action
import com.example.app.test_screen_e.feature.TestScreenEFeature.Effect
import com.example.app.test_screen_e.feature.TestScreenEFeature.News
import com.example.app.test_screen_e.feature.TestScreenEFeature.State
import com.example.app.test_screen_e.feature.TestScreenEFeature.Wish
import io.reactivex.Observable
import io.reactivex.Observable.empty

internal class TestScreenEFeature : BaseFeature<Wish, Action, Effect, State, News>(
    initialState = State(),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl(),
    wishToAction = ::ExecuteWish
) {

    data class State(
        val yourData: Any? = null
    )

    sealed class Action {
        data class ExecuteWish(val wish: Wish) : Action()
    }

    sealed class Wish

    sealed class Effect

    sealed class News

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> =
            empty()
    }

    class ActorImpl : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<Effect> =
            when (action) {
                is ExecuteWish -> executeWish(state, action.wish)
            }

        private fun executeWish(state: State, wish: Wish): Observable<Effect> =
            empty()
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            state
    }

    class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? =
            null
    }
}
