package com.example.echildren.tampil

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.echildren.R
import com.example.echildren.adapter.AnakAdapter
import com.example.echildren.databinding.DaftarBinding
import com.example.echildren.databinding.TampilBinding
import com.example.echildren.model.Daftar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TampilData: AppCompatActivity() {

    private lateinit var binding: TampilBinding
    private lateinit var anakRecyclerView: RecyclerView
    private lateinit var anakArrayList: ArrayList<Daftar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TampilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        anakRecyclerView = findViewById(R.id.anakList)
        anakRecyclerView.layoutManager = LinearLayoutManager(this)
        anakRecyclerView.setHasFixedSize(true)

        anakArrayList = arrayListOf<Daftar>()
        ambilDataAnak()
    }

    private fun ambilDataAnak() {
        val database = Firebase.database
        val myref = database.getReference("daftar")

        myref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (anakSnapshot in snapshot.children){
                        val anak = anakSnapshot.getValue(Daftar::class.java)
                        anakArrayList.add(anak!!)
                    }
                    anakRecyclerView.adapter = AnakAdapter(anakArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}