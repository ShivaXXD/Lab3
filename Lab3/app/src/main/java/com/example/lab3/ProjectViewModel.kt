package com.example.lab3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectViewModel : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(
        listOf(
            Project(name = "Мобільний додаток", description = "iOS/Android розробка", progress = 0.75f),
            Project(name = "Веб-сайт", description = "Корпоративний", progress = 0.40f)
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun addProject(name: String, description: String) {
        val newProject = Project(name = name, description = description)
        _projects.value = _projects.value + newProject
    }

    fun updateProgress(id: String, newProgress: Float) {
        _projects.value = _projects.value.map {
            if (it.id == id) it.copy(progress = newProgress) else it
        }
    }

    fun deleteProject(id: String) {
        _projects.value = _projects.value.filter { it.id != id }
    }

    fun getProject(id: String?): Project? {
        return _projects.value.find { it.id == id }
    }

    fun updateProjectDetails(id: String, newName: String, newDescription: String) {
        _projects.value = _projects.value.map {
            if (it.id == id) it.copy(name = newName, description = newDescription) else it
        }
    }
}