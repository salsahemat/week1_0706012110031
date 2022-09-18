package com.uc.animalsdata

import Adapter.AnimalsAdapter
import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.uc.animalsdata.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CardListener {
    private lateinit var viewBind: ActivityMainBinding
    private val adapter = AnimalsAdapter(GlobalVar.listDataAnimals,this)
    private var jumlah : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        permission()
        setUpRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()
        jumlah = GlobalVar.listDataAnimals.size
        if (jumlah == 0){
            viewBind.textView2.alpha = 1f
        }else{
            viewBind.textView2.alpha = 0f
        }
        adapter.notifyDataSetChanged()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,data?.getStringExtra("delete"),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun permission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun listener(){
        viewBind.addbutton.setOnClickListener{
            val myIntent = Intent(this, AddAnimalsActivity::class.java)
            startActivity(myIntent)
        }


    }


    private fun setUpRecyclerView(){
        val layoutManager = GridLayoutManager(this,1)
        viewBind.listdata.layoutManager = layoutManager
        viewBind.listdata.adapter = adapter
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val myIntent = Intent(this@MainActivity, DeleteAnimalsActivity::class.java).apply {
                    putExtra("position", viewHolder.adapterPosition)
                }
                startActivityForResult(myIntent,1)
            }
        }).attachToRecyclerView(listdata)
    }

    override fun onCardClick(position: Int) {
//
    }

    override fun onDeleteClick(position: Int) {
        val myIntent = Intent(this, DeleteAnimalsActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)

    }

    override fun onEditClick(position: Int) {
        val myIntent = Intent(this, AddAnimalsActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }



}