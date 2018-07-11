@file:JvmName("Filter")

package com.infinitytech.sail.project.projects

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.infinitytech.sail.R
import com.infinitytech.sail.util.extentions.onClick
import com.infinitytech.sail.util.extentions.onMyLayoutFinished
import kotlinx.android.synthetic.main.activity_project_detail.*
import kotlinx.android.synthetic.main.fragment_filter.*

private const val tag = "Filter"
private val d = { msg: String -> Log.d(tag, msg) }

class FilterFragment : Fragment() {

    private var listener: OnFilterFinishListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        d("onCreated")
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        d("onCreateView")
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        view.onMyLayoutFinished {
            d("Filter Listener: State-${view.isShown} size- ${view.measuredWidth} * ${view.measuredHeight}")
        }
        finishBtn.onClick { onFinishFilter(Uri.EMPTY) }
        rootLayout.onClick { onFinishFilter(Uri.EMPTY) }
    }

    fun onFinishFilter(uri: Uri) {
        listener?.onFilterFinish(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFilterFinishListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFilterFinishListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFilterFinishListener {
        fun onFilterFinish(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FilterFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
