package com.tools.androidtools.data.response

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class LoginResponseBean(
        val status: Int,
        val msg: String,
        val data: Any,
        val ok: Any
)

data class Data(
        val id: String,
        val username: String,
        val faceImage: String,
        val faceImageBig: String,
        val nickname: String,
        val qrcode: String
)

class Deserializer : ResponseDeserializable<LoginResponseBean> {
    override fun deserialize(content: String) = Gson().fromJson(content, LoginResponseBean::class.java)
}

