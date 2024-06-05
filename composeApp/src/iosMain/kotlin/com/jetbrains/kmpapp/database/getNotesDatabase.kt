package com.jetbrains.kmpapp.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getNotesDatabase(): NotesDatabase {
    val dbFilePath = NSHomeDirectory() + "/notes.db"
    return Room.databaseBuilder<NotesDatabase>(
        name = dbFilePath,
        factory =  { NotesDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}