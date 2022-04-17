package com.example.echildren.daftar

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.echildren.R
import com.example.echildren.databinding.DaftarBinding
import com.example.echildren.model.Daftar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Daftar : AppCompatActivity() {

    private lateinit var binding: DaftarBinding
    private var sImun: String = ""
    private var dataKey: String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DaftarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imunisasi = arrayOf("Polio", "Campak", "TBC", "Hepatitis")

        val spinImunisasi = findViewById<Spinner>(R.id.spinnerImun)

        val adapterImun =
            ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, imunisasi)
        adapterImun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinImunisasi.setAdapter(adapterImun)

        spinImunisasi.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sImun = parent.getItemAtPosition(position).toString()

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {}
        })



        binding.asaBtnSave.setOnClickListener {
            val nama = binding.asaEdtName.text.toString()
            val usia = binding.asaEdtAge.text.toString()
            val tinggi = binding.asaEdtTinggi.text.toString()

            if (binding.spinnerImun.selectedItem != null){
                sImun = binding.spinnerImun.selectedItem.toString()
            }

            if (nama.isEmpty() || usia.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(this, "Isi data dengan benar", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            simpanData(nama, usia, tinggi, sImun)
        }


    }

    fun simpanData(nama: String, usia: String, tinggi: String, imun: String) {
        //fb
        val database = Firebase.database
        val myRef = FirebaseDatabase.getInstance().getReference("daftar")
        val dataKey = myRef.push().key


        val daftar =
            Daftar(dataKey, nama, usia, tinggi,imun)

        if (dataKey != null) {
            myRef.child(nama).setValue(daftar).addOnCompleteListener {
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}