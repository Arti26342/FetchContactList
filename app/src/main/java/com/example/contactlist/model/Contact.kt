package com.example.contactlist.model

import androidx.databinding.BaseObservable

class Contact : BaseObservable() {
    private var name: String? = null
    private var phoneNumber: String? = null
    private var photoUri: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }


    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }


    fun getPhotoUri(): String? {
        return photoUri
    }

    fun setPhotoUri(photoUri: String?) {
        this.photoUri = photoUri
    }

}