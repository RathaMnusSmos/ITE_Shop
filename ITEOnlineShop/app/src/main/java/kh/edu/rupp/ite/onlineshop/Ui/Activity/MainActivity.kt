package kh.edu.rupp.ite.onlineshop.Ui.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.onlineshop.R
import kh.edu.rupp.ite.onlineshop.Ui.Fragment.HomeFragment
import kh.edu.rupp.ite.onlineshop.Ui.Fragment.ProductFragment
import kh.edu.rupp.ite.onlineshop.Ui.Fragment.ProfileFragment
import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var screenTitle: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        screenTitle = findViewById(R.id.screen_title)
        screenTitle.text = "Home"
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    replaceFragment(HomeFragment())
                    Log.d("test","home")
                    screenTitle = findViewById(R.id.screen_title)
                    screenTitle.text = "Home"

                }
                R.id.product ->{
                    replaceFragment(ProductFragment())
                    Log.d("test","product")
                    screenTitle = findViewById(R.id.screen_title)
                    screenTitle.text = "Product"
                }
                R.id.user ->{
                    replaceFragment(ProfileFragment())
                    Log.d("test","profile")
                    screenTitle = findViewById(R.id.screen_title)
                    screenTitle.text = "Profile"
                }
                R.id.more ->{
                    Log.d("test","more")
                }
            }
            true
        }


    }
    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

}