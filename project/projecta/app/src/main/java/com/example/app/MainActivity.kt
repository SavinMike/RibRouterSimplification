package com.example.app

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.example.app.di.DaggerRootComponent
import com.example.app.di.RootComponent
import com.example.ribrouter.RootConnector
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.ribrouter.connect
import com.example.ribrouter.router.Router
import com.example.ribrouter.router.RouterBuilder
import com.example.screens.FragmentScreen

class MainActivity : RibActivity() {

    private lateinit var rootComponent: RootComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)

        rootComponent =
            DaggerRootComponent
                .factory()
                .create(supportFragmentManager)

        supportFragmentManager.fragmentFactory =
            FragmentFactoryImpl(FragmentFactoriesProvider.factories, rootComponent.fragmentFactoriesDependency())
        super.onCreate(savedInstanceState)

        rootComponent
            .connector()
            .routerOutput
            .subscribe {
                when (it) {
                    is Router.Output.ReadyForUse ->
                        if (savedInstanceState == null) {
                            rootComponent.globalRouter().root(FragmentScreen(TestScreenDFragment::class.java, null))
                        }
                }
            }
    }

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.container)

    override fun createRib(savedInstanceState: Bundle?): Rib =
        RouterBuilder(
            object : Router.Dependency {
                override fun screenRouterFactoryProvider(): ScreenRouterFactoryProvider =
                    rootComponent.screenRouterFactoryProvider()
            }
        ).build(BuildContext.root(savedInstanceState)).also {
            it.connect(
                RootConnector(
                    inputSender = rootComponent.connector().routerInput,
                    outputEmitter = rootComponent.connector().routerOutput
                )
            )
        }
}