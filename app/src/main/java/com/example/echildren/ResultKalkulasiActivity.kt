package com.example.echildren

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result_kalkulasi.*

class ResultKalkulasiActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_kalkulasi)
        skipResultBTN.setOnClickListener {
            startActivity( Intent(this@ResultKalkulasiActivity,  KalkulasiGiziActivity::class.java))
        }
        val bmi = intent.getDoubleExtra("bmi", -1.0)
        if (bmi == -1.0) {
            containerL.visibility= View.GONE
        } else {
            bmiValueTV.text=bmi.toString()
            if (bmi < 18.5) {
                containerL.setBackgroundResource(R.color.colorYellow)
                bmiFlagImgView.setImageResource(R.drawable.exclamationmark)
                bmiLabelTV.text="Anda Terlalu KURUS!"
                commentTV.text="Berikut beberapa saran untuk meningkatkan berat badan anda :"
                advice1IMG.setImageResource(R.drawable.nowater)
                advice1TV.text="Jangan minum banyak air sebelum makan"
                advice2IMG.setImageResource(R.drawable.bigmeal)
                advice2TV.text="Tambah asupan kalori"
                advice3TV.text="Tidur yang cukup"

            } else {
                if (bmi > 25) {
                    containerL.setBackgroundResource(R.color.red)
                    bmiFlagImgView.setImageResource(R.drawable.warning)
                    bmiLabelTV.text="Anda Terlalu GEMUK!"
                    commentTV.text="Berikut beberapa saran untuk menurunkan berat badan anda :"
                    advice1IMG.setImageResource(R.drawable.water)
                    advice1TV.text="Minum air sebelum makan"
                    advice2IMG.setImageResource(R.drawable.twoeggs)
                    advice2TV.text="Makan secukupnya dan mengandung protein tinggi"
                    advice3IMG.setImageResource(R.drawable.nosugar)
                    advice3TV.text="Minum kopi atau teh dan hindari makanan manis"
                }
            }
        }
    }
}