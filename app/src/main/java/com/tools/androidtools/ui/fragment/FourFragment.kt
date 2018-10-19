package com.tools.androidtools.ui.fragment


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.DataPart
import com.google.gson.Gson
import com.qingmei2.rximagepicker.core.RxImagePicker

import com.tools.androidtools.R
import com.tools.androidtools.common.AppContext
import com.tools.androidtools.common.CommonConstance
import com.tools.androidtools.data.request.UploadImageRequestBean
import com.tools.androidtools.data.response.Deserializer
import com.tools.androidtools.data.response.LoginResponseBean
import com.tools.androidtools.data.response.fromJson
import com.tools.androidtools.ui.activity.LoginActivity
import com.tools.androidtools.utils.glide.GlideApp
import com.tools.androidtools.utils.imagepicker.MyImagePicker
import com.tools.androidtools.utils.preference.PreferenceSettings
import com.tools.androidtools.utils.uriToPath
import kotlinx.android.synthetic.main.fragment_four.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("IMPLICIT_CAST_TO_ANY")
/**
 * A simple [Fragment] subclass.
 *
 */
class FourFragment : Fragment() {

    lateinit var userId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        userId = PreferenceSettings.userId
        if (userId.isNotEmpty()) {
            getUserPicImage(userId)
        }
    }

    private fun initListener() {
        mMineHeadIv.setOnClickListener { it ->
            //            context!!.startActivity<LoginActivity>()
            if (userId.isEmpty()) {
                context!!.startActivity<LoginActivity>()
            } else {
                RxImagePicker.create(MyImagePicker::class.java)
                        .openGallery(context!!)
                        .subscribe {
                            val uri = it.uri
//                            val path = uriToPath(uri, context!!)
                            uploadImage(userId, uri)
                        }
            }
        }


    }

    private fun uploadImage(userId: String, uri: Uri) {
        val formatData = listOf("userId" to userId)
        Fuel.upload("http://192.168.0.132:8080/u/uploadFaceBase64", parameters = formatData)
                .dataParts { request, url ->
                    listOf<DataPart>(DataPart(File(uriToPath(uri, context!!)), "file", "image/*"))
                }
                .responseObject(Deserializer()) { request, response, result ->
                    result.fold({ d ->
                        if (d.status == 200) {
                            AppContext.toast("头像上传成功")
                            Log.e("main", "data=${d.data}")
                            val bean = Gson().fromJson<LoginResponseBean>(d.data)
                            val picUrl = CommonConstance.IMAGE_BASE_URL + bean.faceImageBig
                            Log.e("main", "data=$picUrl")
                            GlideApp.with(this)
                                    .load(picUrl)
                                    .circleCrop()
                                    .into(mMineHeadIv)
                        } else {
                            AppContext.toast("登录或注册失败")
                        }
                    }, { err ->
                        Log.e("fuel", "err=$err")
                    })
                }
    }

    private fun getUserPicImage(userId: String) {
        Fuel.post("http://192.168.0.132:8080/u/getFaceImage", listOf("userId" to userId))
                .responseObject(Deserializer()) { request, response, result ->
                    result.fold({
                        if (it.status == 200) {
                            if (it.data.isNotEmpty()) {
                                val picUrl = CommonConstance.IMAGE_BASE_URL + it.data
                                GlideApp.with(this)
                                        .load(picUrl)
                                        .circleCrop()
                                        .into(mMineHeadIv)
                            } else {

                            }
                        } else {
                            AppContext.toast(it.msg)
                        }
                    }, {
                        AppContext.toast(it.response.responseMessage)
                    })
                }
    }


}
