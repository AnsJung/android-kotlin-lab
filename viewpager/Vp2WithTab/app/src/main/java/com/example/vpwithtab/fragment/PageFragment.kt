package com.example.vpwithtab.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import com.example.vpwithtab.R


class PageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val title = arguments?.getString(KEY_TITLE) ?: ""
        tvTitle.text = title
        return view
    }
    companion object {
        private const val KEY_TITLE = "KEY_TITLE"

        fun newInstance(title: String): PageFragment {
            val fragment = PageFragment()
            fragment.arguments = bundleOf(KEY_TITLE to title)
            return fragment
        }
    }
}