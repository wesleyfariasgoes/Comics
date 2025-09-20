package com.example.comics.repository

data class ItemModel(
    val data: DataModel
)

data class DataModel(
    val results: List<ResultModel>
)

data class ResultModel(
    val id: String,
    val title: String,
    val overview: String?,
    val poster_path: String
)
