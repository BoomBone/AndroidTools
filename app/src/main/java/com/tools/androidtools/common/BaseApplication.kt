package com.tools.androidtools.common

import android.app.Application
import android.content.ContextWrapper

/**
 * @author Ting
 * @date 2018/7/loading_12
 * @function Application 类
 */

//全局Context
private lateinit var INSTANCE: Application

open class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

object AppContext : ContextWrapper(INSTANCE)