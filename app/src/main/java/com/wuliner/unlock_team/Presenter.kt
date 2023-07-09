package com.example.slipunlock

import android.view.MotionEvent
import android.widget.ImageView
import com.wuliner.unlock_team.ISlipUnlock
import com.wuliner.unlock_team.databinding.ActivityMainBinding


class Presenter(private val target: ISlipUnlock) {

    //保存model
    var modelsArray = arrayListOf<Model>()
    //保存dot
    var dotArray = ArrayList<ImageView>()
    //保存竖线
    var verticalLineArray = ArrayList<ImageView>()
    //保存横线
    var landscapeLineArray = ArrayList<ImageView>()
    //保存左斜线
    var leftSlashLineArray = ArrayList<ImageView>()
    //保存右斜线
    var rightSlashLineArray = ArrayList<ImageView>()

    //记录上一次被点亮的点的视图
    private var lastSelectedDot: ImageView? = null
    //记录密码
    private val passwordBuilder = StringBuilder()
    //模拟密码
    private val password = "123";
    //记录所有点亮的控件
    private val selectedArray = arrayListOf<ImageView>()



    fun touchEvent(event: MotionEvent, binding: ActivityMainBinding) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                actionDown(event,binding)
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(event, binding)
            }
            MotionEvent.ACTION_UP -> {
                actionUp(event, binding)
                postDalyed()
            }
        }
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


    //判断是否在点上
    private fun isInView(x: Float, y: Float): ImageView? {
        TODO()
    }

    //初始化数据
    fun initData() {

    }

}