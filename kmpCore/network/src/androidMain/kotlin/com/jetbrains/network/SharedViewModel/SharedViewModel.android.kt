package com.jetbrains.network.SharedViewModel

import androidx.lifecycle.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier

actual inline fun <reified T : ViewModel> Module.SharedViewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<T>
): KoinDefinition<T> = viewModel(qualifier, definition)