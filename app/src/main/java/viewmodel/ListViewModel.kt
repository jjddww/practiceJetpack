package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.DogBreed

class ListViewModel: ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed("1","Corgi", "15 years", "breedGroup","temperament", "")
        val dog2 = DogBreed("2","Pomeranian", "16 years", "breedGroup","temperament", "")
        val dog3 = DogBreed("3","Chihuahua", "12 years", "breedGroup","temperament", "")

        val dogList = arrayListOf<DogBreed>(dog1, dog2, dog3) //임시 데이터
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = true
    }

}