package com.example.t1000_ceva.ui.attendant.pay_bills

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.ActivityPayBillsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayBillsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayBillsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frame_stepper) as NavHostFragment
        val navigationController = navHostFragment.navController

        val stepper = binding.stepper
        stepper.setupWithNavController(navigationController)

    }

}