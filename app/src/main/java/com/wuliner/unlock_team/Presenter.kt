package com.wuliner.unlock_team

import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.slipunlock.Model
import com.wuliner.unlock_team.databinding.ActivityMainBinding


class Presenter(private val target: ISlipUnlock) {

    //保存model
    var modelArray = arrayListOf<Model>()
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
            target.changeVisiblity(dotView,true)
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
                if (dotView.visibility == View.VISIBLE) return
                //判断是否是第一个点
                if (lastSelectedDot == null) {
                    target.changeVisiblity(dotView,true)
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
                        target.changeVisiblity(dotView,true)
                        target.changeVisiblity(lineView,true)
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
        //判断密码是否正确
        if (passwordBuilder.toString() == password) {
            //密码正确
            target.changeWord("密码解锁成功")
        } else {
            target.changeWord("密码解锁失败")
            //切换图片
            selectedArray.forEach {
                //找到这个控件对应的model
                for (model in modelArray) {
                    if (model.imageView == it) {
                        target.changeColor(model, false)
                        break
                    }
                }
            }
        }
        passwordBuilder.clear()
    }

    fun postDalyed() {
        Handler().postDelayed(
            {
                selectedArray.forEach {
                    target.changeVisiblity(it,true)
                    //找到这个控件对应的model
                    for (model in modelArray) {
                        if (model.imageView == it) {
                            target.changeColor(model,true)
                            break
                        }
                    }
                }
            }, 500
        )
    }


    //判断是否在点上
    fun isInView(x: Float, y: Float): ImageView? {
        dotArray.forEach {
            if ((x >= it.left && x <= it.right) && (y >= it.top && y <= it.bottom)) {
                return it
            }
        }
        return null
    }

    //初始化数据

    fun initData(binding: ActivityMainBinding) {
        //将九个点的视图保存到数组中
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
        dotArray.forEach {
            modelArray.add(Model(it, R.drawable.dot_normal, R.drawable.dot_selected))
        }
        //竖线
        verticalLineArray = arrayListOf(
            binding.line14,
            binding.line25,
            binding.line36,
            binding.line47,
            binding.line58,
            binding.line69
        )
        verticalLineArray.forEach {
            modelArray.add(Model(it, R.drawable.line_1_normal, R.drawable.line_1_error))
        }
        //横线
        landscapeLineArray = arrayListOf(
            binding.line12,
            binding.line23,
            binding.line45,
            binding.line56,
            binding.line78,
            binding.line89
        )
        landscapeLineArray.forEach {
            modelArray.add(Model(it, R.drawable.line_2_normal, R.drawable.line_2_error))
        }
        //左斜
        leftSlashLineArray =
            arrayListOf(binding.line24, binding.line35, binding.line57, binding.line68)
        leftSlashLineArray.forEach {
            modelArray.add(Model(it, R.drawable.line_4_normal, R.drawable.line_4_error))
        }
        //右斜
        rightSlashLineArray =
            arrayListOf(binding.line15, binding.line26, binding.line48, binding.line59)
        rightSlashLineArray.forEach {
            modelArray.add(Model(it, R.drawable.line_3_normal, R.drawable.line_3_error))
        }

    }

}
