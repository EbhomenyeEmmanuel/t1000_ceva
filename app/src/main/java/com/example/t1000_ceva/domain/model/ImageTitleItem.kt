package com.example.t1000_ceva.domain.model

import android.graphics.drawable.Drawable

open class ImageTitleItem(val image: Drawable?,
                          val title: String)

class LoginTypeImageTitleItem(
    val loginTypes: POSLoginTypes,
    title: String,
    icon: Drawable? = null
) : ImageTitleItem(icon, title)