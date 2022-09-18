package com.uc.animalsdata

import Database.GlobalVar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_animals.*
import kotlinx.android.synthetic.main.animalsitem.cancel_btn
import kotlinx.android.synthetic.main.activity_delete_animals.*
import kotlinx.android.synthetic.main.activity_delete_animals.delete_button
import kotlinx.android.synthetic.main.activity_main.*

class DeleteAnimalsActivity : AppCompatActivity() {
    var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_animals)
        listener()

    }

    private fun listener(){
        cancel_btn.setOnClickListener {
            finish()
        }

        delete_button.setOnClickListener {
            position = intent.getIntExtra("position", -1)
            if (position != -1){
                GlobalVar.listDataAnimals.removeAt(position)
            }
            val intent = Intent()
            intent.putExtra("delete", "Hapus berhasil")
            setResult(RESULT_OK, intent)
            finish()
        }
    }


}