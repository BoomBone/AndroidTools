package com.tools.androidtools.ui.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.google.gson.JsonParser

import com.tools.androidtools.R
import com.tools.androidtools.data.request.LoginRequestBean
import com.tools.androidtools.data.response.Deserializer
import com.tools.androidtools.data.response.LoginResponseBean
import com.tools.androidtools.data.response.fromJson
import com.tools.androidtools.utils.preference.PreferenceSettings

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {

        email_sign_in_button.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            if (verification(emailText, passwordText)) {
                toast("登录")
                login(emailText, passwordText)
            }

        }
    }

    private fun verification(emailText: String, passwordText: String): Boolean {
        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            toast("错误的邮箱格式")
            return false
        }
        if (passwordText.length < 6) {
            toast("密码至少需要6位")
            return false
        }
        return true
    }


    private fun login(email: String, password: String) {
        val requestBean = LoginRequestBean(email, password)
        val requestData = Gson().toJson(requestBean)

        val type = mutableMapOf<String, String>()
        type["Content-Type"] = "application/json"

        Log.e("fuel", "requestData=$requestData")

        Fuel.post("http://192.168.0.132:8080/u/registerOrLogin")
                .body(requestData)
                .header(type)
                .responseObject(Deserializer()) { request, response, result ->
                    result.fold({ d ->
                        if (d.status == 200) {
                            toast("登录或注册成功")
                            Log.e("mian", "data=${d.data}")
                            val bean = Gson().fromJson<LoginResponseBean>(d.data)
                            val userId = bean.id
                            Log.e("main", "userId=$userId")
                            PreferenceSettings.userId = userId
                            finish()
                        } else {
                            toast("登录或注册失败")
                        }
                    }, { err ->
                        Log.e("fuel", "err=$err")
                    })
                }

    }

}
