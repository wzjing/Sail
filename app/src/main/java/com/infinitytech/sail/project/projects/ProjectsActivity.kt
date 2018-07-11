@file:JvmName("ProjectList")

package com.infinitytech.sail.project.projects

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.ActivityOptions
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.infinitytech.sail.R
import com.infinitytech.sail.data.CoverType
import com.infinitytech.sail.data.ListProjectBean
import com.infinitytech.sail.data.source.local.AppDatabase
import com.infinitytech.sail.project.projectdetail.ProjectDetailActivity
import com.infinitytech.sail.util.extentions.*
import com.infinitytech.sail.util.glide.SailGlide
import kotlinx.android.synthetic.main.activity_projects.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.koin.android.ext.android.inject

private const val tag = "ProjectList"
private val d = { msg: String -> Log.d(tag, msg) }
private val i = { msg: String -> Log.i(tag, msg) }

class ProjectsActivity : AppCompatActivity(), FilterFragment.OnFilterFinishListener {

    private val viewModel: ProjectsViewModel by inject()

    private var fabAnim = lazy {
        with(fab) {
            ObjectAnimator.ofFloat(this, View.X, View.Y, Path().apply {
                moveTo(x, y)
                val dx = (filterLayout.centerX - centerX).toFloat()
                val dy = (filterLayout.centerY - centerY).toFloat()
                rCubicTo(dx / 2, 0F, dx, dy / 2, dx, dy)
            }).apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 120
            }
        }
    }

    private val filterAnim
        get() = with(filterLayout) {
            fabAnim.value.apply {
                doOnStart {
                    visibility = View.INVISIBLE
                }
                doOnEnd {
                    fab.hide()
                    visibility = View.VISIBLE
                    ViewAnimationUtils.createCircularReveal(
                            this@with,
                            measuredWidth / 2,
                            measuredHeight / 2,
                            0f,
                            (measuredWidth.sq + measuredHeight.sq).sqrt.toFloat() / 2)
                            .apply {
                                duration = 240
                            }.start()
                    removeAllListeners()
                }
            }
        }


    private val filterAnimReverse
        get() = with(filterLayout) {
            ViewAnimationUtils.createCircularReveal(
                    this,
                    measuredWidth / 2,
                    measuredHeight / 2,
                    (measuredWidth.sq + measuredHeight.sq).sqrt.toFloat() / 2,
                    0f
            ).apply {
                duration = 300
                doOnEnd {
                    visibility = View.GONE
                    fab.show()
                    fabAnim.value.reverse()
                    removeAllListeners()
                }
            }
        }

    private val layoutListener: ViewTreeObserver.OnGlobalLayoutListener =
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    d("--LayoutListener triggered")
                    if (filterLayout.visibility == View.VISIBLE) {
                        filterAnim.start()
                        filterLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                }
            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        @RequiresApi(Build.VERSION_CODES.M)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
//        window.statusBarColor = Color.RED

        val dao = Room.inMemoryDatabaseBuilder(this, AppDatabase::class.java).build()
        dao.projectDao()
        setContentView(R.layout.activity_projects)

        // Remove the bottom value(which is navigation bar height) from SystemWindowInsets
        toolbar.setOnApplyWindowInsetsListener { v, insets ->
            v.onApplyWindowInsets(insets.replaceSystemWindowInsets(
                    insets.systemWindowInsetLeft,
                    insets.systemWindowInsetTop,
                    insets.systemWindowInsetRight,
                    0))
        }
        projectsRecyclerView.layoutManager =
                GridLayoutManager(this, integer(R.integer.list_span_count))

        titleTv.onClick {
            projectsRecyclerView.smoothScrollToPosition(0)
        }

        fab.onClick {
            filterLayout.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
            filterLayout.visibility = View.VISIBLE
        }

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.projects.observe(this) {
            if (contentLoadingProgressBar.visibility == View.VISIBLE) {
                contentLoadingProgressBar.visibility = View.GONE
            }
            projectsRecyclerView.swapAdapter(ProjectAdapter(it!!), true)
            refreshLayout.isRefreshing = false
        }
        viewModel.snackBarText.observe(this) {
            Snackbar.make(coordinatorLayout, it ?: return@observe, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackActionRetry) { viewModel.refresh() }
                    .show()
        }
        viewModel.init()

    }

    override fun onFilterFinish(uri: Uri) {
        filterAnimReverse.start()
    }

}

class ProjectAdapter(private val projectList: List<ListProjectBean>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = projectList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ProjectViewHolder(parent.context.constraintLayout {
                background = ColorDrawable(Color.WHITE)
                stateListAnimator = AnimatorInflater.loadStateListAnimator(context,
                        R.animator.item_state_list_animator)
                iv(id = "projectIv".newId()) {
                    isFocusable = true
                    isClickable = true
                    scaleType = ImageView.ScaleType.FIT_CENTER
                    background = ColorDrawable(Color.WHITE)
                    var start = 0L
                    onTouch { v, event ->
                        this@constraintLayout.onTouchEvent(event)
                        d("EventTime: ${event.eventTime}")
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            post { onTouchEvent(event) }
                            true
                        } else {
                            onTouchEvent(event)
                        }
                    }
                }.lparams(match_parent, match_constraint) {
                    topToTop = PARENT
                    bottomToBottom = PARENT
                    startToStart = PARENT
                    endToEnd = PARENT
                    dimensionRatio = "h,101:79"
                }
            }.lparams(match_parent, wrap_content)) as RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            with((holder as ProjectViewHolder).shotIv) {
                val project = projectList[position]
                SailGlide.with(context)
                        .load(project.covers[CoverType.SIZE_202])
                        .placeholder(ColorDrawable(with(project.colors.first()) {
                            Color.argb(0x5F, r, g, b)
                        }))
                        .fallback(R.drawable.ic_glide_fallback)
                        .error(R.drawable.ic_glide_error)
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(this)

                @RequiresApi(Build.VERSION_CODES.M)
                foreground = RippleDrawable(ColorStateList.valueOf(project.colors.first().rgb),
                        null, null)

                onClick {
                    context.apply {
                        startActivity(
                                intentFor<ProjectDetailActivity>(
                                        "id" to project.id,
                                        "url" to project.covers[CoverType.SIZE_202]),
                                ActivityOptions.makeSceneTransitionAnimation(this as Activity,
                                        it, ProjectDetailActivity.SHARE_COVER).toBundle())
                    }
                }
                Unit
            }

    internal class ProjectViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val shotIv: ImageView = rootView.findViewById("projectIv".id())
    }

}