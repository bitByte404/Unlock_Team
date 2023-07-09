package com.wuliner.unlock_team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.slipunlock.Presenter

class MainActivity : AppCompatActivity(),ISlipUnlock {
    private val presenter = Presenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}