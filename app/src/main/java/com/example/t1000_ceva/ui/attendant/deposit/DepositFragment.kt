package com.example.t1000_ceva.ui.attendant.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentDepositBinding


class DepositFragment : Fragment(R.layout.fragment_deposit) {
    private val binding by viewBinding<FragmentDepositBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPayForDeposit.setOnClickListener {
            navController.navigate(R.id.action_depositFragment_to_attendantMenuFragment)
        }
    }
}