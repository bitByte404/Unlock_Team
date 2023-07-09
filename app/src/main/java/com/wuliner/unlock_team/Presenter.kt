package com.example.slipunlock

import android.view.MotionEvent
import com.wuliner.unlock_team.ISlipUnlock
import com.wuliner.unlock_team.databinding.ActivityMainBinding

class Presenter(private val target: ISlipUnlock) {

    private val model = Model()

    fun saveDotArray(binding: ActivityMainBinding){
        model.saveDotArray(binding)
    }

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

}