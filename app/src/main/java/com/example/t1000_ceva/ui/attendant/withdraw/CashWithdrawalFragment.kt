package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentCashWithdrawalBinding
import com.example.t1000_ceva.databinding.FragmentConfirmAccountNumberBinding
import com.example.t1000_ceva.utils.viewBinding

class CashWithdrawalFragment : Fragment(R.layout.fragment_cash_withdrawal) {
    private val binding by viewBinding<FragmentCashWithdrawalBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCashWithdrawal.setOnClickListener {

        }
    }
}