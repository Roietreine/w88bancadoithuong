package project.rr.w88bancadoithuong.fishgame_features.presentation

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.rr.w88bancadoithuong.fishgame_features.utils.BubbleFishView
import java.util.*


class FishFragment : Fragment() {

    private val handler = Handler()
    private val Interval: Long = 30
    var scores = Bundle()

    var gameView = context?.let { BubbleFishView(it) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        gameView = context?.let { BubbleFishView(it) }
        scores.putString("scores","iskor")



        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post { gameView!!.invalidate() }
            }
        }, 0,Interval)

        return gameView
    }
}