package com.infinitytech.sail.project.projects

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.infinitytech.sail.data.ListProjectBean
import com.infinitytech.sail.data.source.ProjectRepository

class ProjectsViewModel(private val repo: ProjectRepository) : ViewModel() {

    private val tag = this::class.simpleName

    var projects: MutableLiveData<List<ListProjectBean>> = MutableLiveData()

    var snackBarText: MutableLiveData<String> = MutableLiveData()

    fun init() {
        if (projects.value == null) {
            refresh()
        }
    }

    fun refresh() {
        repo.getProjects {
            success {
                projects.value = it
            }

            failed { error ->
                Log.d(tag, "${error.value}: ${error.description}")
                snackBarText.value = "${error.value}: ${error.description}"
            }
        }
    }
}