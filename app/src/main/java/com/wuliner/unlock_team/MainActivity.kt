package com.wuliner.unlock_team

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.slipunlock.Model
import com.example.slipunlock.Presenter
import com.wuliner.unlock_team.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),ISlipUnlock {
    private val presenter = Presenter(this)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        presenter.saveDotArray(binding)

        //给容器添加触摸事件
        binding.container.setOnTouchListener { v, event ->
            presenter.touchEvent(event, binding)
            true
        }
    }

    override fun changeImage(model: Model, rightImage: Int, wrongImage: Int) {
        TODO("Not yet implemented")
    }

    override fun changeVisible(model: Model, ifVisible: Boolean) {
        TODO("Not yet implemented")
    }
}