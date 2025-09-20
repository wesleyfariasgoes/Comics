package com.example.comics.interactor

import com.example.comics.presenter.IPresenter
import com.example.comics.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Interactor(
    val iPresenter: IPresenter,
    val repository: Repository = Repository()
) : IInteractor {

    override suspend fun getMovies() {
        try {
            iPresenter.setupList(repository.getMovies())
        } catch (e: Exception) {
            e.printStackTrace()
            iPresenter.error()
        }
    }

}