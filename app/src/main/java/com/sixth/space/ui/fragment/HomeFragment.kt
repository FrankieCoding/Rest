package com.sixth.space.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sixth.space.R
import com.sixth.space.databinding.FragmentBaseCommonViewpagerBinding
import com.sixth.space.ui.onMenuClickListener
import org.easy.tools.utils.ToastUtils

class HomeFragment(private val listener : onMenuClickListener) : Fragment(), OnClickListener {
    lateinit var binding: FragmentBaseCommonViewpagerBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseCommonViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotList: Array<String?> = resources.getStringArray(R.array.home_array);
        for (str in hotList) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(str))
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                };
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }
        })

        binding.viewPager.adapter= HomeFragmentStateAdapter(this);
        binding.tabLayout.getTabAt(1)?.select();
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }

        })
        binding.imgOpenMenu.setOnClickListener(this);
        binding.imgSearch.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        if (v == binding.imgOpenMenu) {
            listener.onMenuClick();
            return
        }
        if (v == binding.imgSearch) {
            ToastUtils.getInstance().show("imgSearch")
        }
    }
}

class HomeFragmentStateAdapter(fm: Fragment) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return TiktokFragment().newInstance(position);
    }

}