package com.uc.animalsdata

import Database.GlobalVar
import Model.Animals
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.uc.animalsdata.databinding.ActivityAddAnimalsBinding
import kotlinx.android.synthetic.main.activity_add_animals.*

class AddAnimalsActivity : AppCompatActivity() {
    private lateinit var viewBind:ActivityAddAnimalsBinding
    private lateinit var animals: Animals
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLEY
            viewBind.imageview.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAddAnimalsBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }
    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val animals = GlobalVar.listDataAnimals[position]
            viewBind.toolbarAnimals.title = "Edit Animals"
            viewBind.Simpanhewan.text = "Edit"
            viewBind.imageview.setImageURI(Uri.parse(animals.imageUri))
            viewBind.Nama.editText?.setText(animals.nama)
            viewBind.Jenis.editText?.setText(animals.jenis)
            viewBind.Usia.editText?.setText(animals.usia.toString())
        }
    }

    private fun listener(){
        viewBind.imageview.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.Simpanhewan.setOnClickListener{
            var nama= viewBind.Nama.editText?.text.toString().trim()
            var jenis = viewBind.Jenis.editText?.text.toString().trim()
            var usia = 0

            animals = Animals(nama, jenis, usia)
            checker()
        }

        viewBind.toolbarAnimals.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun checker() {
        var isCompleted:Boolean = true

        if(animals.nama!!.isEmpty()){
            viewBind.Nama.error = "Title cannot be empty"
            isCompleted = false
        }else{
            viewBind.Nama.error = ""
        }

        if(animals.jenis!!.isEmpty()){
            viewBind.Jenis.error = "jenis cannot be empty"
            isCompleted = false
        }else{
            viewBind.Jenis.error = ""
        }

        animals.imageUri = image.toString()
//       if(animals.imageUri!!.isEmpty()){
//            //toast
//            Toast.makeText(this, "Picture cannot be empty", Toast.LENGTH_LONG).show()
//           isCompleted = false
//       }

        if(viewBind.Usia.editText?.text.toString().isEmpty() || viewBind.Usia.editText?.text.toString().toInt() < 0)
        {
            viewBind.Usia.error = "usia cannot be empty or 0"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                animals.usia = viewBind.Usia.editText?.text.toString().toInt()
                GlobalVar.listDataAnimals.add(animals)

            }else
            {
                animals.usia = viewBind.Usia.editText?.text.toString().toInt()
                GlobalVar.listDataAnimals[position] = animals
            }
            finish()
        }
    }
}