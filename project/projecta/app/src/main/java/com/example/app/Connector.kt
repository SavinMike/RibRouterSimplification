package com.example.app

import com.example.ribrouter.router.Router
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay

data class Connector(
    val routerInput: Relay<Router.Input> = BehaviorRelay.create(),
    val routerOutput: Relay<Router.Output> = PublishRelay.create()
)