package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentConfirmAccountNumberBinding

class ConfirmAccountNumberFragment : Fragment(R.layout.fragment_confirm_account_number) {
    private val binding by viewBinding<FragmentConfirmAccountNumberBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            navController.navigate(R.id.action_confirmAccountNumberFragment_to_depositFragment)
        }

        binding.btnNo.setOnClickListener {
            navController.popBackStack()
        }
    }

}