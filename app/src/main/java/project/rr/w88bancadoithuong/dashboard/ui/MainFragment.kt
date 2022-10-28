package project.rr.w88bancadoithuong.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import project.rr.w88bancadoithuong.R
import project.rr.w88bancadoithuong.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentMainBinding.inflate(inflater, container, false)
        onButtonClick()
        return binding.root
    }


    private fun onButtonClick() {
        binding.webviewCard.setOnClickListener(this)
        binding.privacyCard.setOnClickListener(this)
        binding.gameCard.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.webviewCard -> findNavController().navigate(R.id.action_mainFragment_to_webviewFragment)
            binding.privacyCard -> findNavController().navigate(R.id.action_mainFragment_to_privacyFragment)
            binding.gameCard -> findNavController().navigate(R.id.action_mainFragment_to_fishFragment)
        }
    }

}