package com.example.jetpackapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsResponse {
    @Expose
    @SerializedName("result")
    var result: ResultEntity? = null
    @Expose
    @SerializedName("status")
    var status: String? = null

    class ResultEntity {
        @Expose
        @SerializedName("news")
        var news: ArrayList<NewsEntity>? = ArrayList()
    }

    class NewsEntity {
        @Expose
        @SerializedName("latitude")
        var latitude: String? = null
        @Expose
        @SerializedName("longitude")
        var longitude: String? = null
        @Expose
        @SerializedName("post_date")
        var postDate: String? = null
        @Expose
        @SerializedName("url_news_image_rectangle")
        var urlNewsImageRectangle: String? = null
        @Expose
        @SerializedName("url_news_image_dalam")
        var urlNewsImageDalam: String? = null
        @Expose
        @SerializedName("url_news_image_luar")
        var urlNewsImageLuar: String? = null
        @Expose
        @SerializedName("video")
        var video: String? = null
        @Expose
        @SerializedName("content")
        var content: String? = null
        @Expose
        @SerializedName("content_short")
        var contentShort: String? = null
        @Expose
        @SerializedName("title")
        var title: String? = null
        @Expose
        @SerializedName("id_news")
        var idNews: Int = 0
    }

}