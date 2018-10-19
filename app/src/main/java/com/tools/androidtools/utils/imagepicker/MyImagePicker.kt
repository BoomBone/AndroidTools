package com.tools.androidtools.utils.imagepicker

import android.content.Context
import com.qingmei2.rximagepicker.entity.Result
import com.qingmei2.rximagepicker.entity.sources.Camera
import com.qingmei2.rximagepicker.entity.sources.Gallery
import io.reactivex.Observable

interface MyImagePicker {
    @Gallery    //打开相册选择图片
    fun openGallery(context: Context): Observable<Result>

    @Camera    //打开相机拍照
    fun openCamera(context: Context): Observable<Result>
}