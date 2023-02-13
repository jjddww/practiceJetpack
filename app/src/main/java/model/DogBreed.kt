package model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogBreed(
    val breedId: String?,
    val dogBreed: String?,
    val lifeSpan: String?,
    val breedGroup: String?,
    val temperament: String?,
    val imageUrl: String?
):Parcelable