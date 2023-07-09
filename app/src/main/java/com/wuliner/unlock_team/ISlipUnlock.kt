package com.wuliner.unlock_team

import com.example.slipunlock.Model

interface ISlipUnlock {
    //切换图片
    fun changeImage(model: Model, rightImage: Int, wrongImage: Int)
    //切换可见状态
    fun changeVisible(model: Model, ifVisible: Boolean)
}