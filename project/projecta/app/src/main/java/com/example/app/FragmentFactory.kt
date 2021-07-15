package com.example.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.app.di.RootDependency

interface FragmentProvider<in T> : (T) -> Fragment

class FragmentFactoryImpl<T>(
    private val providers: @JvmSuppressWildcards Map<Class<out Fragment>, FragmentProvider<T>>,
    private val dependencies: T
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val provider = providers[fragmentClass]
        return provider?.invoke(dependencies) ?: super.instantiate(classLoader, className)
    }
}

fun <T> factory(action: (dependency: T) -> Fragment): FragmentProvider<T> =
    object : FragmentProvider<T> {
        override fun invoke(p1: T): Fragment = action(p1)
    }

class FragmentFactoriesDependency(rootDependency: RootDependency) :
    TestScreenDFragment.Dependency,
    RootDependency by rootDependency

object FragmentFactoriesProvider {
    val factories: Map<Class<out Fragment>, FragmentProvider<FragmentFactoriesDependency>> =
        mapOf(
            TestScreenDFragment::class.java to factory { TestScreenDFragment(it) }
        )
}