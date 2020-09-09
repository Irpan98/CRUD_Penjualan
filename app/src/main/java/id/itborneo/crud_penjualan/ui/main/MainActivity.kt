package id.itborneo.crud_penjualan.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import id.itborneo.crud_penjualan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        getDataAndSendToFragment()
    }

    private fun getDataAndSendToFragment() {

        findNavController(R.id.MainNavHostFragment)
            .setGraph(R.navigation.main_nav_graph, intent.extras)
    }
}