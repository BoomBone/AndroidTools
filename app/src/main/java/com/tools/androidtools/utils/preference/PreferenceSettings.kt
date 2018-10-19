package com.tools.androidtools.utils.preference

import com.dexin.common.util.preference.Preference
import com.tools.androidtools.common.AppContext

/**
 * @author Ting
 * @date 2018/7/12
 * @function 存储的SharePreference值
 */
object PreferenceSettings {
    var userId: String by Preference(AppContext, "userId", "")
}