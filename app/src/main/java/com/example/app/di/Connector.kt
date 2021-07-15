package com.example.app.di

import com.example.ribrouter.router.Router
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay

data class Connector(
    val routerInput: Relay<Router.Input> = PublishRelay.create(),
    val routerOutput: Relay<Router.Output> = PublishRelay.create()
)