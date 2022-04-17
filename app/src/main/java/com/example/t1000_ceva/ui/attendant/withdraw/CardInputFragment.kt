package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentCardInputBinding
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.viewBinding


/**
 * Class for observing when a card is input in the terminal
 */
class CardInputFragment : Fragment(R.layout.fragment_card_input) {
    private val binding by viewBinding<FragmentCardInputBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            binding.infoText.text = "Card is Ok"
            navController.navigate(R.id.action_cardInputFragment_to_pinEntryDialogFragment)
        }, 2000)
    }
}