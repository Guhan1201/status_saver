package com.example.statussaver.model

sealed class TabItem(val displayName : String) {
    data class PhotoTab(val name: String) : TabItem(name)
    data class VedioTab(val name: String) : TabItem(name)
    data class DownloadTab(val name: String) : TabItem(name)

}