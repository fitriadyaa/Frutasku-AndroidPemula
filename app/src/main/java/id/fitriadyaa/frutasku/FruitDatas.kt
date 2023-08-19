package id.fitriadyaa.frutasku

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FruitDatas(
    val name: String,
    val description: String,
    val photo: Int,
    val price: String,
    val store: String,
    val storePhoto: Int,
    val link: String
) : Parcelable

