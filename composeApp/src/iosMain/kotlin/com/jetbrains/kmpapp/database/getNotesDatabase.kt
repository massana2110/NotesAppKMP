package com.jetbrains.kmpapp.database

import platform.Foundation.NSHomeDirectory

fun getNotesDatabase() {
    val dbFile = NSHomeDirectory() + "/notes.db"
    /*return Room.databaseBuilder<NotesDatabase>(
        name = dbFile,
        factory = { NotesDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()*/
}