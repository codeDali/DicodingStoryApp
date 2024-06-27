package com.dicoding.picodiploma.loginwithanimation.helper

import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.response.DetailStoryResponse
import com.dicoding.picodiploma.loginwithanimation.data.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.response.SignUpResponse
import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesResponse
import com.dicoding.picodiploma.loginwithanimation.data.response.UploadResponse
import com.dicoding.picodiploma.loginwithanimation.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun signUp(name: String, email: String, password: String): SignUpResponse {
        return apiService.signUp(name, email, password)
    }

    suspend fun login(email: String,password: String): LoginResponse {
        val response = apiService.login(email, password)
        if (response.error == false) {
            val userModel = UserModel(email, response.loginResult?.token ?: "")
            userPreference.saveSession(userModel)
        }
        return response
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun getStory(): StoriesResponse {
        return apiService.getAllStories()
    }

    suspend fun getStoryId(id: String): DetailStoryResponse {
        return apiService.getStoryId(id)
    }

    suspend fun uploadStory(file: MultipartBody.Part, description: RequestBody): UploadResponse {
        return apiService.uploadStory(file, description)
    }

    suspend fun logout() {
        userPreference.logout()
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository {
            return UserRepository(userPreference, apiService)
        }
    }
}