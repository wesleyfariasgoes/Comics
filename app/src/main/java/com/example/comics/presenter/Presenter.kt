package com.example.comics.presenter

import com.example.comics.repository.DataModel
import com.example.comics.repository.ItemModel
import com.example.comics.view.IView

class Presenter(val iview: IView) : IPresenter {

    override fun setupList(list: DataModel) {
        iview.viewList(
            list = list.results
        )
    }

    override fun error() {
        iview.error()
    }
}