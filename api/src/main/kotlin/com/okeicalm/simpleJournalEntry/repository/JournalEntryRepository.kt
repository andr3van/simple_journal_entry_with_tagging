package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.JOURNAL_ENTRIES
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.JOURNAL_ENTRY_TAGS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface JournalEntryRepository {
    fun findJournalEntriesByTagName(tagName: String): List<JournalEntry>
}

@Repository
class JournalEntryRepositoryImpl(private val dslContext: DSLContext) : JournalEntryRepository {
    override fun findJournalEntriesByTagName(tagName: String): List<JournalEntry> {
        return dslContext
            .select(
                JOURNAL_ENTRIES.ID,
                JOURNAL_ENTRIES.JOURNAL_ID,
                JOURNAL_ENTRIES.ACCOUNT_ID,
                JOURNAL_ENTRIES.SIDE,
                JOURNAL_ENTRIES.VALUE,
            )
            .from(JOURNAL_ENTRIES)
            .join(JOURNAL_ENTRY_TAGS)
            .on(JOURNAL_ENTRIES.ID.eq(JOURNAL_ENTRY_TAGS.JOURNAL_ENTRY_ID))
            .where(JOURNAL_ENTRY_TAGS.TAG_NAME.eq(tagName))
            .fetch()
            .map {
                JournalEntry(
                    id = it[JOURNAL_ENTRIES.ID]!!,
                    journalId = it[JOURNAL_ENTRIES.JOURNAL_ID]!!,
                    accountId = it[JOURNAL_ENTRIES.ACCOUNT_ID]!!,
                    side = it[JOURNAL_ENTRIES.SIDE]!!,
                    value = it[JOURNAL_ENTRIES.VALUE]!!,
                )
            }
    }
}
