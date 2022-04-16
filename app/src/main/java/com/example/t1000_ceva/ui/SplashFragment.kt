package com.example.t1000_ceva.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding<FragmentSplashBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("SplashFragment", "onViewCreated: ")
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            navController.navigate(R.id.action_splashFragment_to_startFragment)
        }, 2000)
    }

}