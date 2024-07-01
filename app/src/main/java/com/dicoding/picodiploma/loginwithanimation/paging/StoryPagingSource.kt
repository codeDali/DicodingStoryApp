package com.dicoding.picodiploma.loginwithanimation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesItem
import com.dicoding.picodiploma.loginwithanimation.data.retrofit.ApiService

class StoryPagingSource(private val apiService: ApiService): PagingSource<Int, StoriesItem>() {

    override fun getRefreshKey(state: PagingState<Int, StoriesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoriesItem> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getStories(page = position, size = params.loadSize)
            val stories = response.listStory
            LoadResult.Page(
                data = stories,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (stories.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}