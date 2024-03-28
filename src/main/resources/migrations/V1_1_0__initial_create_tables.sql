CREATE TABLE IF NOT EXISTS klant_entity (

    id UUID NOT NULL PRIMARY KEY,
    naam VARCHAR(255),
    straat varchar(255),
    woonplaats varchar(255),
    email varchar(255)
);

CREATE TABLE IF NOT EXISTS les_entity (

    id UUID NOT NULL PRIMARY KEY,
    naam VARCHAR(255),
    straat varchar(255),
    woonplaats varchar(255),
    email varchar(255)
);
CREATE TABLE IF NOT EXISTS locatie_entity (

    id UUID NOT NULL PRIMARY KEY,
    naam VARCHAR(255),
    straat varchar(255),
    postCode varchar(255),
    woonplaats varchar(255),
    latitude varchar(255),
    longitude varchar(255)
);
create table les_planning_entity (
    maand bytea not null,
    primary key (maand)
);

create sequence association_value_entry_seq start with 1 increment by 50;

create table association_value_entry (
        id bigint not null,
        association_key varchar(255) not null,
        association_value varchar(255),
        saga_id varchar(255) not null,
        saga_type varchar(255),
        primary key (id)
 );
 create table dead_letter_entry (enqueued_at timestamp(6) with time zone not null, last_touched timestamp(6) with time zone, processing_started timestamp(6) with time zone, sequence_index bigint not null, sequence_number bigint, cause_message varchar(1023), aggregate_identifier varchar(255), cause_type varchar(255), dead_letter_id varchar(255) not null, event_identifier varchar(255) not null, message_type varchar(255) not null, payload_revision varchar(255), payload_type varchar(255) not null, processing_group varchar(255) not null, sequence_identifier varchar(255) not null, time_stamp varchar(255) not null, token_type varchar(255), type varchar(255), diagnostics oid, meta_data oid, payload oid not null, token oid, primary key (dead_letter_id), unique (processing_group, sequence_identifier, sequence_index));

 create table saga_entry (revision varchar(255), saga_id varchar(255) not null, saga_type varchar(255), serialized_saga oid, primary key (saga_id));
