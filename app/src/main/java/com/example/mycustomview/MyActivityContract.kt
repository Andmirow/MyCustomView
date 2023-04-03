package com.example.mycustomview

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract


class MyActivityContract: ActivityResultContract<Unit, String?>() {


    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, FaceCustomActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {

        if (resultCode != Activity.RESULT_OK) return null
        return intent?.extras?.getString(RESULT_KEY)
    }

    companion object {
        const val RESULT_KEY = "my_result_key"
    }
}