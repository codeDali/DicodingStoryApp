package com.dicoding.picodiploma.loginwithanimation

import com.dicoding.picodiploma.loginwithanimation.data.response.StoriesItem

object Dummy {
    fun generateDummyStoryResponse(): List<StoriesItem> {
        val items: MutableList<StoriesItem> = arrayListOf()
        for (i in 0..100) {
            val story = StoriesItem(
                id = i.toString(),
                name = "Story $i",
                description = "Description $i",
                photoUrl = "https://example.com/photo$i.jpg",
                createdAt = "2021-09-14T06:51:25.000Z",
                lon = 0.0,
                lat = 0.0
            )
            items.add(story)
        }
        return items
    }
}