package com.adlubagusi.myfriendapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMyFriendsFragment()

    }
    private fun changeFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
    fun showMyFriendsFragment() {
        changeFragment(supportFragmentManager,
            MyFriendsFragment.newInstance(), R.id.contentFrame)
    }
    fun showMyFriendsAddFragment(){
        changeFragment(supportFragmentManager,
            MyFriendsAddFragment.newInstance(), R.id.contentFrame)
    }
}
