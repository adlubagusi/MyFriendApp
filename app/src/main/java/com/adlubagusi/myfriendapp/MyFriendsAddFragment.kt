package com.adlubagusi.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_add_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
class MyFriendsAddFragment : Fragment() {

    companion object {
        fun newInstance(): MyFriendsAddFragment {
            return MyFriendsAddFragment()
        }
    }

    private var inputName : String = ""
    private var inputEmail : String = ""
    private var inputTelp : String = ""
    private var inputAddress : String = ""
    private var inputGender : String = ""

    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDao? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friends_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initLocalDB() {
        db = AppDatabase.getAppDataBase(activity!!)
        myFriendDao = db?.myFriendDao()
    }

    private fun initView() {
        btnSave.setOnClickListener { inputValidation() }

        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(activity!!,
            R.array.gender_list, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerGender.adapter = adapter
    }

    private fun inputValidation() {
        inputName = edtName.text.toString()
        inputEmail = edtEmail.text.toString()
        inputTelp = edtTelp.text.toString()
        inputAddress = edtAddress.text.toString()
        inputGender = spinnerGender.selectedItem.toString()

        when{
            inputName.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            inputGender.equals("Pilih kelamin") -> showToast("Kelamin harus dipilih")
            inputEmail.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            inputTelp.isEmpty() -> edtTelp.error = "Telp tidak boleh kosong"
            inputGender.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"

            else -> {

                val friend = MyFriend(name = inputName, gender = inputGender, email = inputEmail, telp = inputTelp, address = inputAddress)
                addFriendData(friend)

            }
        }

    }

    private fun addFriendData(friend: MyFriend) : Job {

        return GlobalScope.launch {
            myFriendDao?.addFriends(friend)
            (activity as MainActivity).showMyFriendsFragment()
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}