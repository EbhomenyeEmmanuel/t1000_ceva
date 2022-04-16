package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentConfirmAccountNumberBinding
import com.example.t1000_ceva.databinding.FragmentValidateAccountNumberBinding

class ValidateAccountNumberFragment : Fragment(R.layout.fragment_validate_account_number) {

    private val binding by viewBinding<FragmentValidateAccountNumberBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckAccount.setOnClickListener {
            navController.navigate(R.id.action_validateAccountNumberFragment_to_confirmAccountNumberFragment)
        }
    }

}