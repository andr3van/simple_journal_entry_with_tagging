package com.okeicalm.simpleJournalEntry.entity

data class JournalEntryTag(
    val id: Long = 0,
    val journalEntryId: Long,
    val tagName: String,
)
