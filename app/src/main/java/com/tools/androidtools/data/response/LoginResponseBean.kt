package com.tools.androidtools.data.response

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class LoginResponseBean(
        val id: String,
        val username: String,
        val faceImage: String,
        val faceImageBig: String,
        val nickname: String,
        val qrcode: String
)


