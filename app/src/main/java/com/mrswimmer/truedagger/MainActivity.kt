package com.mrswimmer.truedagger

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.instacart.library.truetime.TrueTimeRx
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "code"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        check.setOnClickListener {
            TrueTimeRx.build()
                    .initializeRx("time.google.com")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val sdf = SimpleDateFormat("HH:mm:ss.SSS")
                        val androidDate = Date()
                        android.text = "Android Time: ${sdf.format(androidDate)}"
                        ntp.text = "NTP Time: ${sdf.format(it)}"
                    }, {})
        }
    }
}
