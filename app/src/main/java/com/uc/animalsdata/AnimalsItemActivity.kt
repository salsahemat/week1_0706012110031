package com.uc.animalsdata

import Database.GlobalVar
import android.app.Activity
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_animals.*
import kotlinx.android.synthetic.main.activity_add_animals.*
import kotlinx.android.synthetic.main.animalsitem.*

class AnimalsItemActivity : AppCompatActivity() {
    private var position: Int = -1

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            imageview.setImageURI(uri)
            GlobalVar.listDataAnimals[position].imageUri = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animalsitem)

        listener()
    }

    private fun listener(){
        toolbarAnimals.getChildAt(1).setOnClickListener {
            finish()
        }

    }


}