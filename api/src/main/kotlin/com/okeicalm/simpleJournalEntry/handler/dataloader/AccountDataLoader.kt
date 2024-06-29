package com.okeicalm.simpleJournalEntry.handler.dataloader

import com.expediagroup.graphql.dataloader.KotlinDataLoader
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import graphql.GraphQLContext
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AccountDataLoader(val repository: AccountRepository) : KotlinDataLoader<ID, AccountType> {
    override val dataLoaderName = "AccountDataLoader"

    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<ID, AccountType> =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                repository
                    .filterByIds(ids.map { it.toString().toLong() })
                    .map { AccountType(it) }
            }
        }
}
