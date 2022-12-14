package fi.metropolia.retrofitparliamentmember

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import fi.metropolia.retrofitparliamentmember.databinding.ActivityMainBinding
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.mainFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}