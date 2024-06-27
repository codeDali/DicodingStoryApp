package com.dicoding.picodiploma.loginwithanimation.view.upload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.response.UploadResponse
import com.dicoding.picodiploma.loginwithanimation.helper.UserRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel (private val repository: UserRepository): ViewModel() {
    val uploadResult = MutableLiveData<UploadResponse>()

    fun uploadStory(file: MultipartBody.Part, description: RequestBody) {
        viewModelScope.launch {
            try {
                val response = repository.uploadStory(file, description)
                uploadResult.postValue(response)
            } catch (e: Exception) {
                uploadResult.postValue(UploadResponse(error = true, message = e.message.toString()))
            }
        }
    }
}