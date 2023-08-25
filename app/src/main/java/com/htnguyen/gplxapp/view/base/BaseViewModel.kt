package com.htnguyen.gplxapp.view.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.htnguyen.gplxapp.view.base.utils.loadImageUrl

open class BaseViewModel : ViewModel() {

}
@BindingAdapter("avatar")
fun showProfileImage(imageView: ImageView, url: String?) {
    imageView.loadImageUrl(url)
}
