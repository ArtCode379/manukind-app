package app.skeleton.product.di

import androidx.room.Room
import app.skeleton.product.data.database.SkeletonDatabase
import org.koin.dsl.module

private const val DB_NAME = "skeleton_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = SkeletonDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<SkeletonDatabase>().cartItemDao() }

    single { get<SkeletonDatabase>().orderDao() }
}