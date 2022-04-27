package com.example.t1000_ceva.ui.attendant.pay_bills

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentPayBillsBinding
import com.example.t1000_ceva.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayBillsFragment : Fragment(R.layout.fragment_pay_bills) {
    private val binding by viewBinding<FragmentPayBillsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val navHostFragment = childFragmentManager.findFragmentById(R.id.frame_stepper) as NavHostFragment
        val navigationController = navHostFragment.navController

        val stepper = binding.stepper
        stepper.setupWithNavController(navigationController)

    }

}