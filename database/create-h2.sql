CREATE TABLE "aid" (
	"id"                 integer GENERATED ALWAYS AS IDENTITY  NOT NULL,
	"name"               varchar(100)   NOT NULL,
	CONSTRAINT "pk_aid_id" PRIMARY KEY ( "id" ),
	CONSTRAINT "idx_aid_name" UNIQUE ( "name" )
 );

CREATE TABLE "family_status" (
	"id"                 integer GENERATED ALWAYS AS IDENTITY  NOT NULL,
	"name"               varchar(100)   NOT NULL,
	CONSTRAINT "pk_family_status_id" PRIMARY KEY ( "id" )
 );

CREATE TABLE "family_type" (
	"id"                 integer GENERATED ALWAYS AS IDENTITY  NOT NULL,
	"name"               varchar(100)   NOT NULL,
	CONSTRAINT "pk_family_type_id" PRIMARY KEY ( "id" )
 );

CREATE TABLE "region" (
	"id"                 integer GENERATED ALWAYS AS IDENTITY  NOT NULL,
	"shortname"          varchar(100)   NOT NULL,
	"fullname"           varchar(255)   NOT NULL,
	CONSTRAINT "pk_region_id" PRIMARY KEY ( "id" ),
	CONSTRAINT "idx_region_fullname" UNIQUE ( "fullname" )
 );

CREATE TABLE "family" (
	"id"                 integer   NOT NULL,
	"status"             integer   NOT NULL,
	"family_type"        integer   NOT NULL,
	"beneficary_id"      integer   ,
	"provider_id"        integer   ,
	"origin"             integer   ,
	"date_of_registeration" date   NOT NULL,
	"lead_by_woman"      boolean  DEFAULT false NOT NULL,
	CONSTRAINT "pk_family_id" PRIMARY KEY ( "id" )
 );

CREATE INDEX "idx_family_status" ON "family" ( "status" );

CREATE INDEX "idx_family_type" ON "family" ( "family_type" );

CREATE INDEX "idx_family_origin" ON "family" ( "origin" );

CREATE INDEX "idx_family_dor" ON "family" ( "date_of_registeration" );

CREATE INDEX "idx_family_lbw" ON "family" ( "lead_by_woman" );

CREATE TABLE "member" (
	"family_id"          integer   NOT NULL,
	"memeber_id"         integer   NOT NULL,
	"birthday"           date   NOT NULL,
	"relationship"       integer   NOT NULL,
	"special_needs"      boolean  DEFAULT false NOT NULL,
	"has_chronic_disease" boolean  DEFAULT false NOT NULL,
	"sex"                integer   NOT NULL,
	"age"                  integer   as datediff('YY',"birthday",current_date()),
	CONSTRAINT "idx_member" PRIMARY KEY ( "family_id", "memeber_id" ),
	CONSTRAINT "unq_member_memeber_id" UNIQUE ( "memeber_id" )
 );

CREATE INDEX "idx_member_sex" ON "member" ( "sex" );

CREATE INDEX "idx_member_relationship" ON "member" ( "relationship" );

CREATE INDEX "idx_member_special_needs" ON "member" ( "special_needs" );

CREATE INDEX "idx_member_chronic_diseases" ON "member" ( "has_chronic_disease" );

CREATE TABLE "delivered_aid" (
	"family_id"          integer   NOT NULL,
	"aid_id"             integer   NOT NULL,
	"date_of_delievered" date   NOT NULL,
	"amount"             integer  DEFAULT 1 NOT NULL,
	CONSTRAINT "idx_delieveried_aid_pk" PRIMARY KEY ( "family_id", "aid_id", "date_of_delievered" )
 );

CREATE INDEX "idx_delieveried_aid_amounts" ON "delivered_aid" ( "amount" );

CREATE INDEX "idx_delieveried_aid_dates" ON "delivered_aid" ( "date_of_delievered" );

CREATE INDEX "idx_delieveried_aid_per_family" ON "delivered_aid" ( "family_id" );

CREATE INDEX "idx_delieveried_aid_per_aid_type" ON "delivered_aid" ( "aid_id" );

ALTER TABLE "delivered_aid" ADD CONSTRAINT "fk_delieveried_aid_aid" FOREIGN KEY ( "aid_id" ) REFERENCES "aid"( "id" ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "delivered_aid" ADD CONSTRAINT "fk_delieveried_aid_family" FOREIGN KEY ( "family_id" ) REFERENCES "family"( "id" ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "family" ADD CONSTRAINT "fk_family" FOREIGN KEY ( "origin" ) REFERENCES "region"( "id" ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "family" ADD CONSTRAINT "fk_family_family_type" FOREIGN KEY ( "family_type" ) REFERENCES "family_type"( "id" ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "family" ADD CONSTRAINT "fk_family_family_status" FOREIGN KEY ( "status" ) REFERENCES "family_status"( "id" ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "member" ADD CONSTRAINT "fk_member" FOREIGN KEY ( "family_id" ) REFERENCES "family"( "id" ) ON DELETE NO ACTION ON UPDATE NO ACTION;

