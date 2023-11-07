package com.odc.ticket.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.odc.ticket.data.room.BranchModel
import com.odc.ticket.data.room.CardModel
import com.odc.ticket.data.room.CurrencyModel
import com.odc.ticket.data.room.CustomerModel
import com.odc.ticket.data.room.EvaluationModel
import com.odc.ticket.data.room.TitleModel
import com.odc.ticket.data.room.IntervalModel
import com.odc.ticket.data.room.MarkCompleteModel
import com.odc.ticket.data.room.TransferTypeModel
import com.odc.ticket.data.room.r_interface.IBranchDao
import com.odc.ticket.data.room.r_interface.ICardDao
import com.odc.ticket.data.room.r_interface.ICurrencyDao
import com.odc.ticket.data.room.r_interface.ICustomerDao
import com.odc.ticket.data.room.r_interface.IEvaluationDao
import com.odc.ticket.data.room.r_interface.IIntervalDao
import com.odc.ticket.data.room.r_interface.IMarkCompleteDao
import com.odc.ticket.data.room.r_interface.ITitleDao
import com.odc.ticket.data.room.r_interface.ITransfertTypeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [BranchModel::class, CardModel::class, CurrencyModel::class, CustomerModel::class, EvaluationModel::class, IntervalModel::class, TitleModel::class, TransferTypeModel::class, MarkCompleteModel::class], version = 4, exportSchema = false)
 abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun branchDao(): IBranchDao
    abstract fun cardDao(): ICardDao
    abstract fun currencyDao(): ICurrencyDao
    abstract fun customerDao(): ICustomerDao
    abstract fun evaluationDao(): IEvaluationDao
    abstract fun intervalDao(): IIntervalDao
    abstract fun titleDao(): ITitleDao
    abstract fun transferTypeDao():ITransfertTypeDao
    abstract fun markCompleteDao():IMarkCompleteDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null
        fun getDatabase(context: Context): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "ticket"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                   // populateDatabase(database.userDao())
                }
            }
        }
//        suspend fun populateDatabase(userDao: IUserDao) {
//            // Delete all content here.
//            //  wordDao.deleteAll()
//            // Add sample words.
//            var user = User(0,"elieoko100","0000")
//            userDao.insert(user)
//            // TODO: Add your own words!
//        }
    }
}