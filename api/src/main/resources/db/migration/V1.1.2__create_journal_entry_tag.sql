use simple_journal_entry_db;

create table journal_entry_tags
(
    id               bigint      not null primary key auto_increment,
    journal_entry_id bigint      not null,
    tag_name         varchar(50) not null,
    foreign key fk_journal_entry (journal_entry_id) references journal_entries (id)
);
