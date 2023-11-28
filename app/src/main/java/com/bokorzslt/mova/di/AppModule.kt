package com.bokorzslt.mova.di

import com.bokorzslt.data.di.dataModule
import com.bokorzslt.domain.di.domainModule
import com.bokorzslt.ui.di.uiModule
import org.koin.dsl.module

val appModule = module {
    includes(listOf(dataModule, domainModule, uiModule))
}