package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentConfirmAccountNumberBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.utils.viewBinding

class ConfirmAccountNumberFragment : Fragment(R.layout.fragment_confirm_account_number) {
    private val binding by viewBinding<FragmentConfirmAccountNumberBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val destination =
            arguments?.get(getString(R.string.destination_type)) as? AttendantDestination
                ?: kotlin.run {
                    navController.popBackStack()
                    return
                }

        binding.btnYes.setOnClickListener {
            when (destination) {
                AttendantDestination.DEPOSIT -> {
                    navController.navigate(R.id.action_confirmAccountNumberFragment_to_depositFragment)
                }
                AttendantDestination.TRANSFER -> {

                }
                AttendantDestination.CASH_WITHDRAW -> {
                    navController.navigate(R.id.action_confirmAccountNumberFragment_to_cashWithdrawalFragment)
                }
            }
        }

        binding.btnNo.setOnClickListener {
            navController.popBackStack()
        }
    }

}