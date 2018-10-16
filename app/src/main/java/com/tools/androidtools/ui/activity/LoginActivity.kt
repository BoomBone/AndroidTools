package com.tools.androidtools.ui.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns

import com.tools.androidtools.R

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
            if (verification(emailText, passwordText)){
                toast("登录")
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

}
