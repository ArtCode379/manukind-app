package shop.manukindapp.com

import android.app.Application
import shop.manukindapp.com.di.dataModule
import shop.manukindapp.com.di.dispatcherModule
import shop.manukindapp.com.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MNKPAApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@MNKPAApp)
            modules(appModules)
        }
    }
}