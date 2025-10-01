package com.bokorzslt.ui.utils

import android.annotation.SuppressLint


@SuppressLint("DefaultLocale")
fun Double.formatAsRating() = String.format(StringUtils.RATING_FORMAT, this)
    .replace(StringUtils.COMMA_SEPARATOR, StringUtils.DOT_SEPARATOR)