CREATE TABLE aid ( 
	id                   integer NOT NULL  PRIMARY KEY autoincrement ,
	name                 varchar(100) NOT NULL    ,
	CONSTRAINT idx_aid_name UNIQUE ( name ) 
 );

CREATE TABLE family_status ( 
	id                   integer NOT NULL  PRIMARY KEY autoincrement ,
	name                 varchar(100) NOT NULL    
 );

CREATE TABLE family_type ( 
	id                   integer NOT NULL  PRIMARY KEY autoincrement ,
	name                 varchar(100) NOT NULL    
 );

CREATE TABLE region ( 
	id                   integer NOT NULL  PRIMARY KEY autoincrement ,
	shortname            varchar(100) NOT NULL    ,
	fullname             varchar(255) NOT NULL    ,
	CONSTRAINT idx_region_fullname UNIQUE ( fullname ) 
 );

CREATE TABLE family ( 
	id                   integer NOT NULL  PRIMARY KEY  ,
	status               integer NOT NULL    ,
	family_type          integer NOT NULL    ,
	beneficary_id        integer     ,
	provider_id          integer NOT NULL    ,
	origin               integer     ,
	date_of_registeration date NOT NULL    ,
	lead_by_woman        boolean NOT NULL DEFAULT false   ,
	FOREIGN KEY ( origin ) REFERENCES region( id )  ,
	FOREIGN KEY ( family_type ) REFERENCES family_type( id ) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY ( status ) REFERENCES family_status( id ) ON DELETE RESTRICT ON UPDATE CASCADE
 );

CREATE INDEX idx_family_status ON family ( status );

CREATE INDEX idx_family_type ON family ( family_type );

CREATE INDEX idx_family_origin ON family ( origin );

CREATE INDEX idx_family_dor ON family ( date_of_registeration );

CREATE INDEX idx_family_lbw ON family ( lead_by_woman );

CREATE TABLE member ( 
	family_id            integer NOT NULL    ,
	memeber_id           integer NOT NULL    ,
	birthday             date NOT NULL    ,
	relationship         integer NOT NULL    ,
	special_needs        boolean NOT NULL DEFAULT false   ,
	has_chronic_disease  boolean NOT NULL DEFAULT false   ,
	sex                  integer NOT NULL    ,
	age                  integer   GENERATED ALWAYS AS ((strftime('%Y', 'now') - strftime('%Y', '1991-11-01')) - (strftime('%m-%d', 'now') < strftime('%m-%d', '1991-11-01'))) VIRTUAL  ,
	CONSTRAINT idx_member PRIMARY KEY ( family_id, memeber_id ),
	CONSTRAINT unq_member_memeber_id UNIQUE ( memeber_id ) ,
	FOREIGN KEY ( family_id ) REFERENCES family( id )  
 );

CREATE INDEX idx_member_sex ON member ( sex );

CREATE INDEX idx_member_relationship ON member ( relationship );

CREATE INDEX idx_member_special_needs ON member ( special_needs );

CREATE INDEX idx_member_chronic_diseases ON member ( has_chronic_disease );

CREATE TABLE delivered_aid ( 
	family_id            integer NOT NULL    ,
	aid_id               integer NOT NULL    ,
	date_of_delievered   date NOT NULL    ,
	amount               integer NOT NULL DEFAULT 1   ,
	CONSTRAINT idx_delieveried_aid_pk PRIMARY KEY ( family_id, aid_id, date_of_delievered ),
	FOREIGN KEY ( aid_id ) REFERENCES aid( id )  ,
	FOREIGN KEY ( family_id ) REFERENCES family( id )  
 );

CREATE INDEX idx_delieveried_aid_amounts ON delivered_aid ( amount );

CREATE INDEX idx_delieveried_aid_dates ON delivered_aid ( date_of_delievered );

CREATE INDEX idx_delieveried_aid_per_family ON delivered_aid ( family_id );

CREATE INDEX idx_delieveried_aid_per_aid_type ON delivered_aid ( aid_id );

