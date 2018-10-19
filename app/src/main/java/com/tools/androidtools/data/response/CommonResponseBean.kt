package com.tools.androidtools.data.response

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class CommonResponseBean(val status: Int,
                              val msg: String,
                              val data: String,
                              val ok: Any)

class Deserializer : ResponseDeserializable<CommonResponseBean> {
    override fun deserialize(content: String) = Gson().fromJson(content, CommonResponseBean::class.java)
}

inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)