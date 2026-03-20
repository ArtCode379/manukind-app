package app.skeleton.product.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.skeleton.product.data.dao.CartItemDao
import app.skeleton.product.data.dao.OrderDao
import app.skeleton.product.data.database.converter.Converters
import app.skeleton.product.data.entity.CartItemEntity
import app.skeleton.product.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SkeletonDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}