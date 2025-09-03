package klimov.example.widget

import android.app.Application

class WidgetApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        start(this)
    }
}