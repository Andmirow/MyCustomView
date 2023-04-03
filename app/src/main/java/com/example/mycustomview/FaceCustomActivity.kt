package com.example.mycustomview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycustomview.databinding.ActivityMainBinding
import com.example.mycustomview.databinding.FaceCustomBinding

class FaceCustomActivity : AppCompatActivity() {

    private lateinit var binding: FaceCustomBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FaceCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.happyButton.setOnClickListener {
            binding.emotionalFaceView.happinessState = CustomFaceView.HAPPY
        }

        binding.sadButton.setOnClickListener {
            binding.emotionalFaceView.happinessState = CustomFaceView.SAD
        }




        binding.button.setOnClickListener {


                val result = Intent().putExtra(
                    MyActivityContract.RESULT_KEY,
                    "FaceCustomActivity is open"
                )
                setResult(Activity.RESULT_OK, result)
                finish()

        }






    }


}