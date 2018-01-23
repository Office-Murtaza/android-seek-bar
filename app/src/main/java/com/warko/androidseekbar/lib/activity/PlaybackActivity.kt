package com.warko.androidseekbar.lib.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.warko.androidseekbar.R

/**
 * Created by warko on 1/23/18.
 */
class PlaybackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
    }

    companion object {
        private const val EXTRA_PATH = "path"

        fun newIntent(context: Context, path: String): Intent =
                Intent(context, PlaybackActivity::class.java).apply {
                    putExtra(EXTRA_PATH, path)
                }
    }

}