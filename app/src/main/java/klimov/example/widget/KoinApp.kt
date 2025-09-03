package klimov.example.widget

import android.app.Application
import klimov.example.widget.widgets.di.widgetModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

val koinModules: List<Module> = listOf(
    widgetModule
)

fun start(app: Application) {
    startKoin {
        androidContext(app)
        modules(koinModules)
    }
}