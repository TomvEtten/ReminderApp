package hva.nl.reminderapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderRoomDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var reminderRoomDatabaseInstance: ReminderRoomDatabase? = null

        fun getDatabase(context: Context): ReminderRoomDatabase? {
            if (reminderRoomDatabaseInstance != null) {
                return reminderRoomDatabaseInstance
            }
            synchronized(ReminderRoomDatabase::class.java) {
                reminderRoomDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return reminderRoomDatabaseInstance

        }
    }

}