package com.example.t1000_ceva.ui.attendant

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.showSnack
import com.example.t1000_ceva.databinding.FragmentAttendantMenuBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.domain.model.AttendantMenuTypes
import com.example.t1000_ceva.domain.model.ImageTitleItem
import com.example.t1000_ceva.ui.adapter.ImageTitleAdapter
import com.example.t1000_ceva.utils.viewBinding


class AttendantMenuFragmentImageTitleItem(
    val attendantMenuTypes: AttendantMenuTypes,
    title: String,
    icon: Drawable? = null
) : ImageTitleItem(icon, title)

class AttendantMenuFragment : Fragment(R.layout.fragment_attendant_menu) {
    private val binding by viewBinding<FragmentAttendantMenuBinding>()

    private val attendantTypeList by lazy {
        arrayListOf<AttendantMenuFragmentImageTitleItem>(
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.DEPOSIT,
                getString(R.string.deposit),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.TRANSFER,
                getString(R.string.transfer),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.AIRTIME,
                getString(R.string.airtime),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.PAY_BILLS,
                getString(R.string.pay_bills),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.WITHDRAW,
                getString(R.string.withdraw),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            AttendantMenuFragmentImageTitleItem(
                AttendantMenuTypes.NEXT,
                getString(R.string.next),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = binding.attendantList
        list.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        list.adapter = ImageTitleAdapter(requireActivity(), attendantTypeList) {
            when (attendantTypeList[it].attendantMenuTypes) {
                AttendantMenuTypes.DEPOSIT -> {
                    navController.navigate(
                        R.id.action_attendantMenuFragment_to_validateAccountNumberFragment,
                        bundleOf(Pair(getString(R.string.destination_type), AttendantDestination.DEPOSIT))
                    )
                }
                AttendantMenuTypes.TRANSFER -> {
                    //Same as deposit
                    navController.navigate(
                        R.id.action_attendantMenuFragment_to_validateAccountNumberFragment,
                        bundleOf(Pair(getString(R.string.destination_type), AttendantDestination.TRANSFER))
                    )
                }
                AttendantMenuTypes.AIRTIME -> {
                    navController.navigate(R.id.action_attendantMenuFragment_to_airtimePurchaseFragment)
                }
                AttendantMenuTypes.PAY_BILLS -> {
                    navController.navigate(R.id.action_attendantMenuFragment_to_payBillsActivity)
                }
                AttendantMenuTypes.WITHDRAW -> {
                    navController.navigate(R.id.action_attendantMenuFragment_to_withdrawFragment)
                }
                AttendantMenuTypes.NEXT -> {
                }
                else -> showSnack("Option unavailable")
            }

        }
    }
}