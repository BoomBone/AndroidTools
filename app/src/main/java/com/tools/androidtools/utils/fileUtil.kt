package com.tools.androidtools.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log

fun uriToPath(uri: Uri, context: Context): String? {
    if (DocumentsContract.isDocumentUri(context, uri)) {
        val documentId = DocumentsContract.getDocumentId(uri)
        Log.e("main", "docId=$documentId")
        if ("com.android.providers.media.documents" == uri.authority) {
            Log.e("main", "uriType=1")
            val id = documentId.split(":")[1];
            val selection = MediaStore.Images.Media._ID + "=" + id;
            return getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, context)
        } else if ("com.android.providers.downloads.documents" == uri.authority) {
            Log.e("main", "uriType=2")
            //Log.d(TAG, uri.toString());
            val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    documentId.toLong());
            val imagePath = getImagePath(contentUri, null, context);
            return imagePath
        }
    } else if ("content".equals(uri.scheme, true)) {
        //Log.d(TAG, "content: " + uri.toString());
        return getImagePath(uri, null, context)
    }
    return null
}

fun getImagePath(uri: Uri, selection: String?, context: Context): String? {
    var path: String? = null
    val cursor: Cursor? = context.contentResolver.query(uri, null, selection, null, null)
    if (cursor != null) {
        if (cursor.moveToFirst()) {
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        }
        cursor.close()
    }
    return path

}
