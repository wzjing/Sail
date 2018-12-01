package com.infinitytech.sail.di

import com.infinitytech.sail.project.projectdetail.ProjectViewModel
import com.infinitytech.sail.project.projects.ProjectsViewModel
import com.infinitytech.sail.data.source.ProjectRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by wzjing on 2018/1/8 at Designer.
 */
val project_module = module {
    single { ProjectRepository() }
    viewModel { ProjectsViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
}