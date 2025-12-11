package com.example.vpwithtab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vpwithtab.fragment.PageFragment

class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    val titles = listOf("홈", "검색", "설정")
    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(titles[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}