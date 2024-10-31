package com.okeicalm.simpleJournalEntry.entity

data class JournalEntry(
    val id: Long,
    val journalId: Long,
    val side: Byte,
    val accountId: Long,
    val value: Int,
    val tags: List<JournalEntryTag> = emptyList(),
) {
    companion object {
        fun create(
            side: Byte,
            accountId: Long,
            value: Int,
            tags: List<String>? = null,
        ): JournalEntry {
            val journalEntryTags = tags?.map {
                JournalEntryTag(journalEntryId = 0, tagName = it)
            } ?: emptyList()

            return JournalEntry(
                id = 0,
                journalId = 0,
                side = side,
                accountId = accountId,
                value = value,
                tags = journalEntryTags,
            )
        }
    }
}
