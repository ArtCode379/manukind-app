package shop.manukindapp.com.di

import androidx.room.Room
import shop.manukindapp.com.data.database.MNKPADatabase
import org.koin.dsl.module

private const val DB_NAME = "mnkpa_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = MNKPADatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<MNKPADatabase>().cartItemDao() }

    single { get<MNKPADatabase>().orderDao() }
}