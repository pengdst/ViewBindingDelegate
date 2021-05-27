package io.github.pengdst.viewbindingdelegatesample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.viewbindingdelegatesample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by viewBindings()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }
}