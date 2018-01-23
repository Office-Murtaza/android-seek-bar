package com.warko.androidseekbar.sample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.warko.androidseekbar.R
import com.warko.androidseekbar.lib.activity.PlaybackActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnChoose.setOnClickListener {
            startActivityForResult(Intent.createChooser(Intent().apply {
                type = "video/*"
                action = Intent.ACTION_GET_CONTENT
            }, "Choose video"), REQUEST_VIDEO_STUFF)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_VIDEO_STUFF) {
                val selectedVideoUri = data?.data
                val selectedVideoPath = getPath(selectedVideoUri)
                selectedVideoPath?.let {
                    startActivity(PlaybackActivity.newIntent(this, it))
                }
            }
        }
    }

    private fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        cursor.use {
            return if (it != null) {
                // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
                // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                it.moveToFirst()
                it.getString(columnIndex)
            } else {
                null
            }
        }
    }

    companion object {
        private const val REQUEST_VIDEO_STUFF = 666
    }

}
