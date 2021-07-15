package com.example.ribrouter

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class RootConnector<Input, Output>(
    outputEmitter: Consumer<Output>,
    inputSender: ObservableSource<Input> = PublishRelay.create(),
) : Connectable<Input, Output> {

    override val input: Relay<Input> = PublishRelay.create()
    override val output: Relay<Output> = PublishRelay.create()

    init {
        Observable.wrap(inputSender)
            .subscribe(input::accept)

        output.subscribe(outputEmitter)
    }
}

fun <Input, Output> Connectable<Input, Output>.connect(
    connectable: Connectable<Input, Output>,
): Disposable {
    val compositeDisposable = CompositeDisposable()
    compositeDisposable.add(connectable.input.subscribe(input::accept))
    compositeDisposable.add(output.subscribe(connectable.output::accept))
    return compositeDisposable
}
