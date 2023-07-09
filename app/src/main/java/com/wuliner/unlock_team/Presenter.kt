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
                model.actionDown(event,binding)
            }
            MotionEvent.ACTION_MOVE -> {
                model.actionMove(event, binding)
            }
            MotionEvent.ACTION_UP -> {
                model.actionUp(event, binding)
                model.postDalyed()
            }
        }
    }

}