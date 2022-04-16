package com.example.t1000_ceva.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

val Fragment.navController
    get() = findNavController()

fun Fragment.showSnack(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view!!, message, duration).show()