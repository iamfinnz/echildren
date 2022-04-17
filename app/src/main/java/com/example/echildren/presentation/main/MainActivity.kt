package com.example.echildren.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.echildren.KalkulasiGiziActivity
import com.example.echildren.R
import com.example.echildren.ResultKalkulasiActivity
import com.example.echildren.daftar.Daftar
import com.example.echildren.databinding.ActivityMainBinding
import com.example.echildren.model.User
import com.example.echildren.presentation.user.UserActivity
import com.example.echildren.tampil.TampilData
import com.example.echildren.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_POSITION = "extra_position"
    }
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var userDatabase: DatabaseReference
    private lateinit var materialDatabase: DatabaseReference
    private var currentUser: FirebaseUser? = null


    private var listenerUser = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            hideLoading()
            val user = snapshot.getValue(User::class.java)
            user?.let {
                mainBinding.apply {
                    tvNameUserMain.text = it.nameUser

                    Glide
                        .with(this@MainActivity)
                        .load(it.avatarUser)
                        .placeholder(android.R.color.darker_gray)
                        .into(ivAvatarMain)
                }
            }
        }


        override fun onCancelled(error: DatabaseError) {
            hideLoading()
            Log.e("MainActivity", "[onCancelled] - ${error.message}")
            showDialogError(this@MainActivity, error.message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //Init
        userDatabase = FirebaseDatabase.getInstance().getReference("users")
        currentUser = FirebaseAuth.getInstance().currentUser

        getDataFirebase()
        onAction()
    }

    private fun getDataFirebase() {
        showLoading()
        userDatabase
            .child(currentUser?.uid.toString())
            .addValueEventListener(listenerUser)

    }

    private fun showLoading() {
        mainBinding.swipeMain.isRefreshing = true
    }

    private fun hideLoading() {
        mainBinding.swipeMain.isRefreshing = false
    }

    private fun onAction() {
        mainBinding.apply {
            ivAvatarMain.setOnClickListener {
                startActivity<UserActivity>()
            }
            daftarAnak.setOnClickListener{
                startActivity<Daftar>()
            }
            cardTampil.setOnClickListener{
                startActivity<TampilData>()
            }
            kalkulator.setOnClickListener{
                startActivity<KalkulasiGiziActivity>()
            }

        }

    }
}