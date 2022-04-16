package com.example.t1000_ceva.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.showSnack
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentStartBinding
import com.example.t1000_ceva.model.LoginTypeImageTitleItem
import com.example.t1000_ceva.model.POSLoginTypes
import com.example.t1000_ceva.ui.adapter.ImageTitleAdapter
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.viewmodels.FetchUserState
import com.example.t1000_ceva.viewmodels.MainViewModel
import kotlinx.coroutines.flow.onEach

class StartFragment : Fragment(R.layout.fragment_start) {
    private val binding by viewBinding<FragmentStartBinding>()
    private val mainViewModel: MainViewModel by viewModels()
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
                    mainViewModel.downloadUserDetails()
                }
                POSLoginTypes.SUPERVISOR -> {

                }
                else -> showSnack("Option unavailable")

            }
        }

        observeDetails()
    }

    private fun observeDetails() {
        mainViewModel.fetchUserDetailsState.onEach {
            when (it) {
                is FetchUserState.Loading -> {
//                    requireActivity().showProgress(
//                    "Loading User Details",
//                    "Please wait"
//                )
                }
                is FetchUserState.Error -> {
//                    requireActivity().onErrorMessage(
//                        R.string.error_title,
//                        Exception(getString(R.string.details_not_fetch))
//                    )
                }
                is FetchUserState.Success -> {
                    navController.navigate(R.id.action_startFragment_to_attendantMenuFragment)
                    //                    requireActivity().dismissProgress()
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
}