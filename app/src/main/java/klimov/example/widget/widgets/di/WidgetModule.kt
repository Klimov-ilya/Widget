package klimov.example.widget.widgets.di

import klimov.example.widget.widgets.configuration.AppWidgetConfigurationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val widgetModule = module {
    viewModel {
        AppWidgetConfigurationViewModel()
    }
}