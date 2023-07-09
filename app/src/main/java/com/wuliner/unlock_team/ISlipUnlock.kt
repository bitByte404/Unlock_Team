package com.wuliner.unlock_team

import android.widget.ImageView
import com.example.slipunlock.Model
import com.wuliner.unlock_team.databinding.ActivityMainBinding

interface ISlipUnlock {
    var binding: ActivityMainBinding

    //改变颜色
    fun changeColor(model: Model, ifRight: Boolean)

    //改变标题的字
    fun changeWord(msg: String)

    //改变控件可见性
    fun changeVisiblity(imageView: ImageView, isVisible: Boolean)
}