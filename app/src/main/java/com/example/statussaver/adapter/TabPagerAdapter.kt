package com.example.statussaver.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.statussaver.model.TabItem
import com.example.statussaver.ui.fragment.ImageFragment

class TabPagerAdapter(
    fragment: FragmentActivity,
    private val tabItem: List<TabItem>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = tabItem.size

    override fun createFragment(position: Int): Fragment {
        return ImageFragment()
    }
}


