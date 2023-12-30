package com.example.dependencyinjection.networking

import com.example.dependencyinjection.Constants

class UrlProvider {

    fun getBaseUrl1(): String {
        return Constants.BASE_URL
    }

    fun getBaseUrl2(): String {
        return "base_url"
    }
}