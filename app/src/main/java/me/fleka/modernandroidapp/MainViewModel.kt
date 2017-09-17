package me.fleka.modernandroidapp

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import me.fleka.modernandroidapp.data.OnDataReadyCallback
import me.fleka.modernandroidapp.data.OnRepositoryReadyCallback
import me.fleka.modernandroidapp.data.RepoModel
import me.fleka.modernandroidapp.uimodels.Repository

/**
 * Created by Mladen Rakonjac on 8/26/17.
 */
class MainViewModel : ViewModel() {
    var repoModel: RepoModel = RepoModel()

    val text = ObservableField("old data")

    val isLoading = ObservableField(false)

    var repositories = ArrayList<Repository>()

    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }

    fun getRepositories() {
        isLoading.set(true)
        repoModel.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories = data
            }
        })
    }
}