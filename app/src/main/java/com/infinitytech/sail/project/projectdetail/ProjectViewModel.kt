package com.infinitytech.sail.project.projectdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infinitytech.sail.data.ProjectBean
import com.infinitytech.sail.data.source.ProjectRepository

/**
 * Created by wzjing on 2018/1/11 at Designer.
 */
class ProjectViewModel(private val repo: ProjectRepository) : ViewModel() {
    private var project: MutableLiveData<ProjectBean> = MutableLiveData()
    var snackBarText: MutableLiveData<String> = MutableLiveData()

    fun getProject(id: Long): LiveData<ProjectBean> {
        if (project.value == null) {
            project = MutableLiveData()
            refresh(id)
        }
        return project
    }

    fun refresh(id: Long) {
        repo.getProject(id) {
            success {
                project.value = it
            }

            failed { error ->
                snackBarText.value = "${error.value}: ${error.description}"
            }
        }
    }
}