/*
 * This file is generated by jOOQ.
 */
package com.okeicalm.simpleJournalEntry.infra.db.tables


import com.okeicalm.simpleJournalEntry.infra.db.SimpleJournalEntryDb
import com.okeicalm.simpleJournalEntry.infra.db.indexes.JOURNAL_ENTRIES_FK_ACCOUNT
import com.okeicalm.simpleJournalEntry.infra.db.indexes.JOURNAL_ENTRIES_FK_JOURNAL
import com.okeicalm.simpleJournalEntry.infra.db.keys.JOURNAL_ENTRIES_IBFK_1
import com.okeicalm.simpleJournalEntry.infra.db.keys.JOURNAL_ENTRIES_IBFK_2
import com.okeicalm.simpleJournalEntry.infra.db.keys.JOURNAL_ENTRY_TAGS_IBFK_1
import com.okeicalm.simpleJournalEntry.infra.db.keys.KEY_JOURNAL_ENTRIES_PRIMARY
import com.okeicalm.simpleJournalEntry.infra.db.tables.Accounts.AccountsPath
import com.okeicalm.simpleJournalEntry.infra.db.tables.JournalEntryTags.JournalEntryTagsPath
import com.okeicalm.simpleJournalEntry.infra.db.tables.Journals.JournalsPath
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.JournalEntriesRecord

import kotlin.collections.Collection
import kotlin.collections.List

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class JournalEntries(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, JournalEntriesRecord>?,
    parentPath: InverseForeignKey<out Record, JournalEntriesRecord>?,
    aliased: Table<JournalEntriesRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<JournalEntriesRecord>(
    alias,
    SimpleJournalEntryDb.SIMPLE_JOURNAL_ENTRY_DB,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of
         * <code>simple_journal_entry_db.journal_entries</code>
         */
        val JOURNAL_ENTRIES: JournalEntries = JournalEntries()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<JournalEntriesRecord> = JournalEntriesRecord::class.java

    /**
     * The column <code>simple_journal_entry_db.journal_entries.id</code>.
     */
    val ID: TableField<JournalEntriesRecord, Long?> = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column
     * <code>simple_journal_entry_db.journal_entries.journal_id</code>.
     */
    val JOURNAL_ID: TableField<JournalEntriesRecord, Long?> = createField(DSL.name("journal_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>simple_journal_entry_db.journal_entries.side</code>.
     */
    val SIDE: TableField<JournalEntriesRecord, Byte?> = createField(DSL.name("side"), SQLDataType.TINYINT.nullable(false), this, "")

    /**
     * The column
     * <code>simple_journal_entry_db.journal_entries.account_id</code>.
     */
    val ACCOUNT_ID: TableField<JournalEntriesRecord, Long?> = createField(DSL.name("account_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>simple_journal_entry_db.journal_entries.value</code>.
     */
    val VALUE: TableField<JournalEntriesRecord, Int?> = createField(DSL.name("value"), SQLDataType.INTEGER.nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<JournalEntriesRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<JournalEntriesRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<JournalEntriesRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>simple_journal_entry_db.journal_entries</code>
     * table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>simple_journal_entry_db.journal_entries</code>
     * table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>simple_journal_entry_db.journal_entries</code> table
     * reference
     */
    constructor(): this(DSL.name("journal_entries"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, JournalEntriesRecord>?, parentPath: InverseForeignKey<out Record, JournalEntriesRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, JOURNAL_ENTRIES, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class JournalEntriesPath : JournalEntries, Path<JournalEntriesRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, JournalEntriesRecord>?, parentPath: InverseForeignKey<out Record, JournalEntriesRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<JournalEntriesRecord>): super(alias, aliased)
        override fun `as`(alias: String): JournalEntriesPath = JournalEntriesPath(DSL.name(alias), this)
        override fun `as`(alias: Name): JournalEntriesPath = JournalEntriesPath(alias, this)
        override fun `as`(alias: Table<*>): JournalEntriesPath = JournalEntriesPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else SimpleJournalEntryDb.SIMPLE_JOURNAL_ENTRY_DB
    override fun getIndexes(): List<Index> = listOf(JOURNAL_ENTRIES_FK_ACCOUNT, JOURNAL_ENTRIES_FK_JOURNAL)
    override fun getIdentity(): Identity<JournalEntriesRecord, Long?> = super.getIdentity() as Identity<JournalEntriesRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<JournalEntriesRecord> = KEY_JOURNAL_ENTRIES_PRIMARY
    override fun getReferences(): List<ForeignKey<JournalEntriesRecord, *>> = listOf(JOURNAL_ENTRIES_IBFK_1, JOURNAL_ENTRIES_IBFK_2)

    private lateinit var _journals: JournalsPath

    /**
     * Get the implicit join path to the
     * <code>simple_journal_entry_db.journals</code> table.
     */
    fun journals(): JournalsPath {
        if (!this::_journals.isInitialized)
            _journals = JournalsPath(this, JOURNAL_ENTRIES_IBFK_1, null)

        return _journals;
    }

    val journals: JournalsPath
        get(): JournalsPath = journals()

    private lateinit var _accounts: AccountsPath

    /**
     * Get the implicit join path to the
     * <code>simple_journal_entry_db.accounts</code> table.
     */
    fun accounts(): AccountsPath {
        if (!this::_accounts.isInitialized)
            _accounts = AccountsPath(this, JOURNAL_ENTRIES_IBFK_2, null)

        return _accounts;
    }

    val accounts: AccountsPath
        get(): AccountsPath = accounts()

    private lateinit var _journalEntryTags: JournalEntryTagsPath

    /**
     * Get the implicit to-many join path to the
     * <code>simple_journal_entry_db.journal_entry_tags</code> table
     */
    fun journalEntryTags(): JournalEntryTagsPath {
        if (!this::_journalEntryTags.isInitialized)
            _journalEntryTags = JournalEntryTagsPath(this, null, JOURNAL_ENTRY_TAGS_IBFK_1.inverseKey)

        return _journalEntryTags;
    }

    val journalEntryTags: JournalEntryTagsPath
        get(): JournalEntryTagsPath = journalEntryTags()
    override fun `as`(alias: String): JournalEntries = JournalEntries(DSL.name(alias), this)
    override fun `as`(alias: Name): JournalEntries = JournalEntries(alias, this)
    override fun `as`(alias: Table<*>): JournalEntries = JournalEntries(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): JournalEntries = JournalEntries(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): JournalEntries = JournalEntries(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): JournalEntries = JournalEntries(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): JournalEntries = JournalEntries(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): JournalEntries = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): JournalEntries = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): JournalEntries = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): JournalEntries = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): JournalEntries = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): JournalEntries = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): JournalEntries = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): JournalEntries = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): JournalEntries = where(DSL.notExists(select))
}
