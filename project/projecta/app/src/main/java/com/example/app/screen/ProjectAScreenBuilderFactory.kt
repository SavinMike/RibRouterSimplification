package com.example.app.screen

import androidx.fragment.app.FragmentManager
import com.example.app.di.ScreenFactoryDependencyProvider
import com.example.fragmentrib.fragment_container.FragmentContainerRouterFactory
import com.example.ribrouter.ScreenRouterFactoryProvider
import com.example.screens.FragmentScreen
import com.example.screens.ScreenA
import com.example.screens.ScreenB
import com.example.screens.ScreenC
import com.example.screens.ScreenE

fun ProjectAScreenBuilderFactory(
    dependencyProvider: ScreenFactoryDependencyProvider,
    fragmentManager: FragmentManager
): ScreenRouterFactoryProvider =
    ScreenRouterFactoryProvider {
        ScreenA::class.java resolveBy lazy { ScreenARouterFactory(dependencyProvider) }
        ScreenB::class.java resolveBy lazy { ScreenBRouterFactory(dependencyProvider) }
        ScreenC::class.java resolveBy lazy { ScreenCRouterFactory(dependencyProvider) }
        FragmentScreen::class.java resolveBy lazy {
            FragmentContainerRouterFactory(
                dependencyProvider,
                fragmentManager
            )
        }
        ScreenE::class.java resolveBy lazy {
            ScreenERouterFactory(
                dependencyProvider,
                dependencyProvider,
                fragmentManager
            )
        }
    }