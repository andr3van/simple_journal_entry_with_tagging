package com.okeicalm.simpleJournalEntry.handler.query

import com.expediagroup.graphql.server.operations.Query
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.repository.JournalEntryRepository
import org.springframework.stereotype.Component

@Component
class JournalEntryQuery(private val repository: JournalEntryRepository) : Query {
    fun findJournalEntriesByTagName(tagName: String): List<JournalEntryType> {
        val journalEntries = repository.findJournalEntriesByTagName(tagName)
        return journalEntries.map { JournalEntryType(it) }
    }
}
