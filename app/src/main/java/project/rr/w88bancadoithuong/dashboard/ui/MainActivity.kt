package project.rr.w88bancadoithuong.dashboard.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.navigation.findNavController
import project.rr.w88bancadoithuong.R

class MainActivity : AppCompatActivity() {

    companion object{
        fun getStartIntent (context: Context) = Intent(context, MainActivity::class.java)
    }

    private var bckToExit = false

    private val currentFragment by lazy {
        findNavController(R.id.frag_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    override fun onBackPressed() {
        if (currentFragment.currentDestination?.id == R.id.mainFragment) {
            if (bckToExit) {
                finishAffinity()
                return
            }
            bckToExit = true
            Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ bckToExit = false }, 2000)
        } else {
            findNavController(R.id.frag_view).navigateUp()
        }
    }

}