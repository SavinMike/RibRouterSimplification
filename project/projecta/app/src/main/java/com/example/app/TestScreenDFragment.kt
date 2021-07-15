package com.example.app

import android.os.Bundle
import com.badoo.ribs.android.RibFragment
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.example.app.test_screen_d.TestScreenD
import com.example.app.test_screen_d.TestScreenDBuilder

class TestScreenDFragment(val dependency: Dependency) : RibFragment() {

    interface Dependency : TestScreenD.RootDependency

    inner class DependencyAdapter :
        TestScreenD.Dependency,
        TestScreenD.RootDependency by dependency

    override fun createRib(savedInstanceState: Bundle?): Rib =
        TestScreenDBuilder(DependencyAdapter()).build(BuildContext.root(savedInstanceState))
}