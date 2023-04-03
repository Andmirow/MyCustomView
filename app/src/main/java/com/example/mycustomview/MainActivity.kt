package com.example.mycustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.result.launch
import androidx.fragment.app.FragmentManager
import com.example.mycustomview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val activityLauncher = registerForActivityResult(MyActivityContract()) { result ->
            binding.textView2.text = result
        }

        binding.button2.setOnClickListener {
            activityLauncher.launch()
        }

        binding.button4.setOnClickListener {
            supportFragmentManager.setFragmentResultListener("requestKey",this){ key, bundle ->
                binding.textView2.text = bundle.getString("bundleKey")
            }

//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container_view, fragment)
//                .addToBackStack(null)
//                .commit()



        }







    }

}