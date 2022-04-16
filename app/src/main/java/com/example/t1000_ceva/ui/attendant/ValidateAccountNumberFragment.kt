package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentValidateAccountNumberBinding
import com.example.t1000_ceva.utils.viewBinding

class ValidateAccountNumberFragment : Fragment(R.layout.fragment_validate_account_number) {

    private val binding by viewBinding<FragmentValidateAccountNumberBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckAccount.setOnClickListener {
            navController.navigate(R.id.action_validateAccountNumberFragment_to_confirmAccountNumberFragment)
        }
    }

}