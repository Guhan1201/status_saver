package com.example.statussaver.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.statussaver.`package`.FileRepository
import java.io.File


class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private val fileRepository = FileRepository(application.applicationContext)
    private val _imageList: MutableLiveData<List<File>> = MutableLiveData()
    val imageList: LiveData<List<File>>
        get() = _imageList


    init {
        _imageList.value = fileRepository.getImage()
    }

}