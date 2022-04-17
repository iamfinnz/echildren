package com.example.echildren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.echildren.R
import com.example.echildren.daftar.Daftar
import org.jetbrains.anko.find
import org.w3c.dom.Text

class AnakAdapter(private val anakList: ArrayList<com.example.echildren.model.Daftar>) : RecyclerView.Adapter<AnakAdapter.AnakHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnakAdapter.AnakHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
        parent,false)

        return AnakHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnakAdapter.AnakHolder, position: Int) {
        val currItem = anakList[position]

        holder.nama.text = currItem.nama
        holder.usia.text = currItem.age
        holder.tinggi.text = currItem.tinggi
        holder.imun.text = currItem.imun
    }

    override fun getItemCount(): Int {
        return anakList.size
    }

    class AnakHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nama: TextView = itemView.findViewById(R.id.nama)
        val usia: TextView = itemView.findViewById(R.id.usia)
        val tinggi: TextView = itemView.findViewById(R.id.tinggi)
        val imun: TextView = itemView.findViewById(R.id.imun)
    }

}