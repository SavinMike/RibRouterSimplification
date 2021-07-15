package com.example.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.router.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class FragmentScreen(
    val fragmentClass: Class<out Fragment>,
    val arguments: Bundle?,
): Screen, ProjectAScreen