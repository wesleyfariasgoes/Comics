package com.example.comics.presenter

import com.example.comics.repository.DataModel
import com.example.comics.repository.ItemModel

interface IPresenter {

    fun setupList(list: DataModel)

    fun error()
}