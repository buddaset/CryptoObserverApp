package com.example.cryptoobserverapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.example.cryptoobserverapp.R
import com.example.cryptoobserverapp.databinding.ActivityMainBinding
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsFragment
import com.example.cryptoobserverapp.presentation.coin_list.CoinListFragment
import com.example.cryptoobserverapp.presentation.main.navigation.Navigator

class MainActivity : AppCompatActivity() , Navigator {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                add(R.id.fragment_container, CoinListFragment.newInstance())

            }
        }


    }

    override fun showCoinDetailScreen(fSym: String) {
        launchFragment(CoinDetailsFragment.newInstance(fSym))

    }

    override fun goBack() {
        onBackPressed()
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
}