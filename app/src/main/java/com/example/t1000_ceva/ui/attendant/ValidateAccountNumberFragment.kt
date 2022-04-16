package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentValidateAccountNumberBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.utils.viewBinding

class ValidateAccountNumberFragment : Fragment(R.layout.fragment_validate_account_number) {

    private val binding by viewBinding<FragmentValidateAccountNumberBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO(Pass data in bundle after viewModel call)
        val destination =
            arguments?.get(getString(R.string.destination_type)) as? AttendantDestination
                ?: kotlin.run {
                    navController.popBackStack()
                    return
                }

        binding.btnCheckAccount.setOnClickListener {
            navController.navigate(
                R.id.action_validateAccountNumberFragment_to_confirmAccountNumberFragment,
                bundleOf(Pair(getString(R.string.destination_type), destination))
            )
        }
    }

}