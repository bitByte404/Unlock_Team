package com.example.slipunlock

import android.view.MotionEvent
import android.widget.ImageView
import com.wuliner.unlock_team.R
import com.wuliner.unlock_team.databinding.ActivityMainBinding

class Model {
    /**
     * 数据
     */
    //保存9个点
    private var dotArray = arrayListOf<ImageView>()

    //记录上一次被点亮的点的视图
    private var lastSelectedDot: ImageView? = null

    private var selectedDotArray = arrayListOf<ImageView?>()
    private var selectedLineArray = arrayListOf<ArrayList<ImageView?>>()

    private var passwordBuilder = StringBuilder()
    private val password = "123"

    private var errLineArray = listOf(
        R.drawable.line_1_error,
        R.drawable.line_2_error,
        R.drawable.line_3_error,
        R.drawable.line_4_error
    )

    fun saveDotArray(binding: ActivityMainBinding) {
        //将9个点的视图保存到数组中
        dotArray = arrayListOf(
            binding.dot1,
            binding.dot2,
            binding.dot3,
            binding.dot4,
            binding.dot5,
            binding.dot6,
            binding.dot7,
            binding.dot8,
            binding.dot9
        )
    }

    /**
     * 数据处理
     */

    fun actionDown(event: MotionEvent, binding: ActivityMainBinding) {
    }

    fun actionMove(event: MotionEvent, binding: ActivityMainBinding) {
    }

    fun actionUp(event: MotionEvent, binding: ActivityMainBinding) {
    }

    fun postDalyed(){

    }

    /**
     * 判断某个触摸点是否在某个原点内部
     */
    private fun isInView(x: Float, y: Float): ImageView? {
        TODO()
    }
}