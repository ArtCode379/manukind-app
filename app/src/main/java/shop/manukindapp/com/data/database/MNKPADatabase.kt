package shop.manukindapp.com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shop.manukindapp.com.data.dao.CartItemDao
import shop.manukindapp.com.data.dao.OrderDao
import shop.manukindapp.com.data.database.converter.Converters
import shop.manukindapp.com.data.entity.CartItemEntity
import shop.manukindapp.com.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MNKPADatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}