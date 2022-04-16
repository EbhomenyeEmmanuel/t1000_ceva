package com.example.t1000_ceva.ui.attendant.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentDepositBinding
import com.example.t1000_ceva.utils.viewBinding


class DepositFragment : Fragment(R.layout.fragment_deposit) {
    private val binding by viewBinding<FragmentDepositBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPayForDeposit.setOnClickListener {

        }
    }
}