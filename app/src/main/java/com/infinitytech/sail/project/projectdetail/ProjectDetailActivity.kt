@file:JvmName("ProjectDetail")

package com.infinitytech.sail.project.projectdetail

import androidx.lifecycle.Observer
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.ViewCompat
import androidx.appcompat.app.AppCompatActivity
import android.transition.TransitionInflater
import android.util.Log
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.infinitytech.sail.R
import com.infinitytech.sail.data.AvatarType
import com.infinitytech.sail.data.CoverType
import com.infinitytech.sail.util.glide.GlideApp
import com.infinitytech.sail.util.extentions.onClick
import kotlinx.android.synthetic.main.activity_project_detail.*
import org.koin.android.ext.android.inject

private const val tag = "ProjectDetail"
private val d = { msg: String -> Log.d(tag, msg) }
private val i = { msg: String -> Log.i(tag, msg) }

class ProjectDetailActivity : AppCompatActivity() {

    companion object {
        const val SHARE_COVER = "cover"
    }

    private val viewModel: ProjectViewModel by inject()
    private val id = lazy { intent.getLongExtra("id", 0) }
    private val url = lazy { intent.getStringExtra("url") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.explore)
        setContentView(R.layout.activity_project_detail)
        ViewCompat.setTransitionName(projectCoverIv, SHARE_COVER)
        backBtn.onClick {
            finishAfterTransition()
        }

        GlideApp.with(this)
                .load(url.value)
                .onlyRetrieveFromCache(true)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        startViewModel(resource)
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        startViewModel(null)
                        return false
                    }
                })
                .into(projectCoverIv)
    }

    fun startViewModel(placeholder: Drawable?) {
        viewModel.getProject(id.value).observe(this@ProjectDetailActivity, Observer {
            GlideApp.with(this@ProjectDetailActivity)
                    .load(it?.covers!![CoverType.SIZE_404] ?: return@Observer)
                    .placeholder(placeholder)
                    .error(R.drawable.ic_glide_error)
                    .into(projectCoverIv)
            titleTv.text = it.name
            descriptionTv.text = it.description
            GlideApp.with(this)
                    .load(it.owners.first().images[AvatarType.SIZE_230])
                    .into(avatarIv)
            ownerNameTv.text = it.owners.first().displayName
            ownerCountryTv.text = "${it.owners.first().city}, ${it.owners.first().country}"
        })

        viewModel.snackBarText.observe(this, Observer {
            Snackbar.make(rootView, it ?: return@Observer, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackActionRetry) { viewModel.refresh(id.value) }.show()
        })
    }
}
