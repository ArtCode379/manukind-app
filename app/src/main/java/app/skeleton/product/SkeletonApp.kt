package app.skeleton.product

import android.app.Application
import app.skeleton.product.di.dataModule
import app.skeleton.product.di.dispatcherModule
import app.skeleton.product.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SkeletonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@SkeletonApp)
            modules(appModules)
        }
    }
}