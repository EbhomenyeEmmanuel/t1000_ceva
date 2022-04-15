package com.example.t1000_ceva.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.showSnack
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentStartBinding
import com.example.t1000_ceva.model.LoginTypeImageTitleItem
import com.example.t1000_ceva.model.POSLoginTypes
import com.example.t1000_ceva.ui.adapter.ImageTitleAdapter

class StartFragment : Fragment(R.layout.fragment_start) {
    private val binding by viewBinding<FragmentStartBinding>()

    private val loginTypeList by lazy {
        arrayListOf(
            LoginTypeImageTitleItem(
                POSLoginTypes.ATTENDANT,
                getString(R.string.attendant),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            LoginTypeImageTitleItem(
                POSLoginTypes.SUPERVISOR,
                getString(R.string.supervisor),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = binding.list
        list.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        list.adapter = ImageTitleAdapter(requireActivity(), loginTypeList) {
            when (loginTypeList[it].loginTypes) {
                POSLoginTypes.ATTENDANT -> {
                    navController.navigate(R.id.action_startFragment_to_attendantMenuFragment)
                }
                POSLoginTypes.SUPERVISOR -> {

                }
                else -> showSnack("Option unavailable")

            }
        }
    }
}