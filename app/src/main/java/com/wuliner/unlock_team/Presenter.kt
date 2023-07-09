package com.example.slipunlock

import android.view.MotionEvent
import android.view.View
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
                actionDown(event, binding)
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
        //判断触摸点是否在原点内部
        val dotView = isInView(event.x, event.y)
        if (dotView != null) {
            //点亮原点
            dotView.visibility = View.VISIBLE
            //记录下来
            lastSelectedDot = dotView
            //记录密码
            passwordBuilder.append(dotView.tag as String)
            //保存
            selectedArray.add(dotView)
        }
    }

    fun actionMove(event: MotionEvent, binding: ActivityMainBinding) {
        //判断触摸点是否在原点内部
        val dotView = isInView(event.x, event.y)
        //处理在点亮的点内部触发move事件
        if (lastSelectedDot != dotView) {
            if (dotView != null) {
                //判断是否是第一个点
                if (lastSelectedDot == null) {
                    dotView.visibility = View.VISIBLE
                    lastSelectedDot = dotView
                    //记录密码
                    passwordBuilder.append(dotView.tag as String)
                    selectedArray.add(dotView)
                } else {
                    //判断路线是否有
                    //获取上一个点和当前点的tag值 形成线的tag
                    val lastTag = (lastSelectedDot!!.tag as String).toInt()
                    val currentTag = (dotView.tag as String).toInt()
                    //形成线的tag small*10 +big
                    val lineTag =
                        if (lastTag < currentTag) lastTag * 10 + currentTag else currentTag * 10 + lastTag
                    //获取lineTag对应的控件
                    val lineView =
                        binding.container.findViewWithTag<ImageView>("$lineTag")
                    if (lineView != null) {
                        //有路线
                        dotView.visibility = View.VISIBLE
                        lineView.visibility = View.VISIBLE
                        lastSelectedDot = dotView
                        //记录密码
                        passwordBuilder.append(dotView.tag as String)
                        //保存
                        selectedArray.add(dotView)
                        selectedArray.add(lineView)
                    }
                }
            }
        }
    }

    fun actionUp(event: MotionEvent, binding: ActivityMainBinding) {

    }

    fun postDalyed() {

    }


    //判断是否在点上
    private fun isInView(x: Float, y: Float): ImageView? {
        dotArray.forEach {
            if ((x >= it.left && x <= it.right) && (y >= it.top && y <= it.bottom)) {
                return it
            }
        }
        return null
    }
}

//初始化数据
fun initData() {

}
