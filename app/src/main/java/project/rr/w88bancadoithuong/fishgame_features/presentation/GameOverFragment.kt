package project.rr.w88bancadoithuong.fishgame_features.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import project.rr.w88bancadoithuong.R
import project.rr.w88bancadoithuong.databinding.FragmentGameOverBinding


class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!
    var getString: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        getString = arguments?.getString("scores")

        @SuppressLint("SetTextI18n")
        binding.scoreText.text = "Score: $getString"
        playAgainFun()


        return binding.root
    }


    private fun playAgainFun() {
        binding.yesButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_fishFragment)
        }
        binding.noButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainFragment)
        }
    }
}