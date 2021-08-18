package com.blank.game_suit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.game_suit.R
import com.blank.game_suit.classes.replaceFragmentFromActivity
import com.blank.game_suit.data.UserData
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

/**
 * The number of pages (wizard steps) to show in this demo.
 */
private const val NUM_PAGES = 3

class ViewPagerFragment(private val fragContainer: Int) : Fragment(R.layout.fragment_view_pager) {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var mPager: ViewPager2
    private lateinit var arrowButton: ImageView
    private var isFilled: Boolean = false
    private var username: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle ViewPager2 onBackPressed
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mPager.currentItem == 0) {
                    // If the user is currently looking at the first step, allow the system to handle the
                    // Back button. This calls finish() on this activity and pops the back stack.

                } else {
                    // Otherwise, select the previous step.
                    mPager.currentItem = mPager.currentItem - 1
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrowButton = view.findViewById(R.id.arrowButton)

        val dotsIndicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = view.findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(requireActivity())
        mPager.adapter = pagerAdapter
        dotsIndicator.setViewPager2(mPager)

        arrowButton.setOnClickListener {
            mPager.currentItem = mPager.currentItem + 1
        }

    }

    override fun onResume() {
        super.onResume()
        if (isFilled) {
            lateinit var userData: UserData
            arrowButton.setOnClickListener {
                userData = UserData(username, "player")
                replaceFragmentFromActivity(
                    parentFragmentManager, ChooseOpponent(fragContainer), fragContainer,
                    userData
                )
            }
        }
    }

    fun showbtn(boolean: Boolean) {
        arrowButton.isVisible = boolean
        isFilled = boolean
        onResume()
    }

    fun storeUsernameFromChildFragment(user: String) {
        username = user
    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
        private val pages = listOf(
            LandingFirst(),
            LandingSecond(),
            LandingThird.newInstance(
                this@ViewPagerFragment::showbtn,
                this@ViewPagerFragment::storeUsernameFromChildFragment
            )
        )

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return pages[position]
        }
    }

}