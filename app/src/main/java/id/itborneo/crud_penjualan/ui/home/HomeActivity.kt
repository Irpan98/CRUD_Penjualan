package id.itborneo.crud_penjualan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import id.itborneo.crud_penjualan.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getDataAndSendToFragment()

        supportActionBar?.title

        attachBottomNav()

    }

    private fun getDataAndSendToFragment() {

        findNavController(R.id.homeNavHostFragment)
            .setGraph(R.navigation.home_nav_graph, intent.extras)
    }

    private fun attachBottomNav() {
        val navController = Navigation.findNavController(this, R.id.homeNavHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }

}