package com.dexin.common.util.preference

import android.app.Activity
import android.content.SharedPreferences
import com.tools.androidtools.common.AppContext
import com.tools.androidtools.common.CommonConstance

/**
 * @author Ting
 * @date 2018/7/12
 * @function 清除SharePreference
 */
fun clearSharePreferenceTableName(tableName: String = CommonConstance.SHARE_PREFERENCE_DEFAULT_TABLE_NAME): Boolean {
    val sharedPreferences: SharedPreferences = AppContext.getSharedPreferences(tableName, Activity.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.clear()
    return editor.commit()
}