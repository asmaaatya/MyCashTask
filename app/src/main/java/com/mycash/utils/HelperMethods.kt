package com.mycash.utils

import android.view.View

object HelperMethods {

    fun View.visible() = this.apply { visibility = View.VISIBLE }

    fun View.gone() = this.apply { visibility = View.GONE }

}