package com.htnguyen.gplxapp.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.htnguyen.gplxapp.base.utils.loadImageUrl

open class BaseViewModel : ViewModel() {

}
@BindingAdapter("avatar")
fun showProfileImage(imageView: ImageView, url: String?) {
    imageView.loadImageUrl(url)
}
