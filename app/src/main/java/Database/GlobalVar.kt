package Database

import Model.Animals

class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        val listDataAnimals = ArrayList<Animals>()
    }
}