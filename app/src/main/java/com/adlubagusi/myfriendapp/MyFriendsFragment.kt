package com.adlubagusi.myfriendapp
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_fragment.*

class MyFriendsFragment : Fragment() {

    companion object {
        fun newInstance(): MyFriendsFragment {
            return MyFriendsFragment()
        }
    }
    private var friendList : List<MyFriend>? = null
    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDao? = null

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friends_fragment,
            container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }
    private fun initLocalDB(){
        db = AppDatabase.getAppDataBase(activity!!)
        myFriendDao = db?.myFriendDao()
    }
    private fun initView() {
        fabAddFriend.setOnClickListener{ (activity as MainActivity).showMyFriendsAddFragment()}
        getFriendData()
    }
    private fun getFriendData(){
        friendList = ArrayList()
        myFriendDao?.getAllFriend()?.observe(this, Observer { r ->
            friendList = r
            when{
                friendList?.size == 0 -> showToast("Belum ada data")
                else -> {
                    showFriend()
                }
            }
        })
    }
    private fun showToast(message: String){
        Toast.makeText(activity!!,message, Toast.LENGTH_SHORT).show()
    }
    /*private fun friendDataSimulation(){
        listFriend = ArrayList()
        listFriend.add(MyFriend("", "Laki-laki", "bagus@email.com", "081246319759", "Karangploso"))
        listFriend.add(MyFriend("Utaha", "Perempuan", "utaha@kasumigaoka.com", "081234567891", "Malang"))
    }*/
    private fun showFriend() {
        listMyFriends.layoutManager = LinearLayoutManager(activity)
        listMyFriends.adapter = MyFriendAdapter(activity!!, (friendList as ArrayList<MyFriend>?)!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}