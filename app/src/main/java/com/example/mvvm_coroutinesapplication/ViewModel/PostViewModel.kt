package com.example.mvvm_coroutinesapplication.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutinesapplication.Model.Post
import com.example.mvvm_coroutinesapplication.Repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository): ViewModel() {

    val postMutableLiveData:MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            try {
                val response = postRepository.getPost()
                postMutableLiveData.value = response
               // Log.e("Response>>", postMutableLiveData.value.toString())
            }catch (exp:Exception){
               Log.e("Exception>>", exp.message!!)
            }

        }
    }
}