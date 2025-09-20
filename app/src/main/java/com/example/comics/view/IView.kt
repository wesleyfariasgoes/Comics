package com.example.comics.view

import com.example.comics.repository.ResultModel

interface IView {

    fun viewList(list: List<ResultModel>)

    fun refrash()

    fun error()

}