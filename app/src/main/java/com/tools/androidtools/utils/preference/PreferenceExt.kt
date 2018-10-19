package com.dexin.common.util.preference

import android.content.Context
import com.tools.androidtools.common.CommonConstance
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Ting
 * @date 2018/7/12
 * @function SharePreference的扩展方法,ReadWriteProperty读写代理
 * @param context 上下文
 * @param name sp的键值
 * @param default sp的value值
 * @param prefName sp的文件名
 */

class Preference<T>(val context: Context,
                    val name: String,
                    val default: T,
                    val prefName: String = CommonConstance.SHARE_PREFERENCE_DEFAULT_TABLE_NAME) : ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name)
    }

    //取值 键name
    private fun findPreference(name: String): T {
        return when (default) {
            is Long -> prefs.getLong(name, default)
            is Int -> prefs.getInt(name, default)
            is String -> prefs.getString(name, default)
            is Boolean -> prefs.getBoolean(name, default)

            else -> throw IllegalArgumentException("Unsupported type!!")
        } as T
    }


    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    //存值 键name,值value
    private fun putPreference(name: String, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(name, value)
                is Boolean -> putBoolean(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)

                else -> throw IllegalArgumentException("Unsupported type!!")
            }
        }.commit()
    }
}
