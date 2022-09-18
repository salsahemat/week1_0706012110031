package Model
import android.os.Parcel
import android.os.Parcelable

data class Animals(
    var nama:String?,
    var jenis:String?,
    var usia: Int?,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }
    var imageUri: String = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(jenis)
        usia?.let { parcel.writeInt(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animals> {
        override fun createFromParcel(parcel: Parcel): Animals {
            return Animals(parcel)
        }

        override fun newArray(size: Int): Array<Animals?> {
            return arrayOfNulls(size)
        }
    }
}
