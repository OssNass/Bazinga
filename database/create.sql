drop database if exists bazinga;

drop user if exists bazingaadmin;

create database bazinga with encoding 'utf8' lc_ctype 'ar_SY.utf8' lc_collate 'ar_SY.utf8' template 'template0';
\c bazinga


CREATE  TABLE public.aid ( 
	id                   serial  NOT NULL ,
	name                 varchar(100)  NOT NULL ,
	CONSTRAINT pk_aid_id PRIMARY KEY ( id ),
	CONSTRAINT idx_aid_name UNIQUE ( name ) 
 );

CREATE  TABLE public.family_status ( 
	id                   serial  NOT NULL ,
	name                 varchar(100)  NOT NULL ,
	CONSTRAINT pk_family_status_id PRIMARY KEY ( id )
 );

CREATE  TABLE public.family_type ( 
	id                   serial  NOT NULL ,
	name                 varchar(100)  NOT NULL ,
	CONSTRAINT pk_family_type_id PRIMARY KEY ( id )
 );

CREATE  TABLE public.region ( 
	id                   serial  NOT NULL ,
	shortname            varchar(100)  NOT NULL ,
	fullname             varchar(255)  NOT NULL ,
	CONSTRAINT pk_region_id PRIMARY KEY ( id ),
	CONSTRAINT idx_region_fullname UNIQUE ( fullname ) 
 );

CREATE  TABLE public.delivered_aid ( 
	family_id            integer  NOT NULL ,
	aid_id               integer  NOT NULL ,
	date_of_delievered   date  NOT NULL ,
	amount               integer DEFAULT 1 NOT NULL ,
	CONSTRAINT idx_delieveried_aid_pk PRIMARY KEY ( family_id, aid_id, date_of_delievered )
 );

CREATE INDEX idx_delieveried_aid_amounts ON public.delivered_aid ( amount );

CREATE INDEX idx_delieveried_aid_dates ON public.delivered_aid ( date_of_delievered );

CREATE INDEX idx_delieveried_aid_per_family ON public.delivered_aid ( family_id );

CREATE INDEX idx_delieveried_aid_per_aid_type ON public.delivered_aid ( aid_id );

CREATE  TABLE public.family ( 
	id                   integer  NOT NULL ,
	status               integer  NOT NULL ,
	family_type          integer  NOT NULL ,
	beneficary_id        integer   ,
	provider_id          integer  NOT NULL ,
	origin               integer   ,
	date_of_registeration date  NOT NULL ,
	lead_by_woman        boolean DEFAULT false NOT NULL ,
	CONSTRAINT pk_family_id PRIMARY KEY ( id )
 );

CREATE INDEX idx_family_status ON public.family ( status );

CREATE INDEX idx_family_type ON public.family ( family_type );

CREATE INDEX idx_family_origin ON public.family ( origin );

CREATE INDEX idx_family_dor ON public.family ( date_of_registeration );

CREATE INDEX idx_family_lbw ON public.family ( lead_by_woman );

CREATE  TABLE public.member ( 
	family_id            integer  NOT NULL ,
	memeber_id           integer  NOT NULL ,
	birthday             date  NOT NULL ,
	relationship         integer  NOT NULL ,
	special_needs        boolean DEFAULT false NOT NULL ,
	has_chronic_disease  boolean DEFAULT false NOT NULL ,
	sex                  integer  NOT NULL ,
	CONSTRAINT idx_member PRIMARY KEY ( family_id, memeber_id ),
	CONSTRAINT unq_member_memeber_id UNIQUE ( memeber_id ) 
 );

CREATE INDEX idx_member_sex ON public.member ( sex );

CREATE INDEX idx_member_relationship ON public.member ( relationship );

CREATE INDEX idx_member_special_needs ON public.member ( special_needs );

CREATE INDEX idx_member_chronic_diseases ON public.member ( has_chronic_disease );

ALTER TABLE public.delivered_aid ADD CONSTRAINT fk_delieveried_aid_aid FOREIGN KEY ( aid_id ) REFERENCES public.aid( id );

ALTER TABLE public.delivered_aid ADD CONSTRAINT fk_delieveried_aid_family FOREIGN KEY ( family_id ) REFERENCES public.family( id );

ALTER TABLE public.family ADD CONSTRAINT fk_family FOREIGN KEY ( origin ) REFERENCES public.region( id );

ALTER TABLE public.family ADD CONSTRAINT fk_family_family_type FOREIGN KEY ( family_type ) REFERENCES public.family_type( id ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.family ADD CONSTRAINT fk_family_provider FOREIGN KEY ( provider_id ) REFERENCES public.member( memeber_id ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.family ADD CONSTRAINT fk_family_beneficiary FOREIGN KEY ( beneficary_id ) REFERENCES public.member( memeber_id ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.family ADD CONSTRAINT fk_family_family_status FOREIGN KEY ( status ) REFERENCES public.family_status( id ) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.member ADD CONSTRAINT fk_member FOREIGN KEY ( family_id ) REFERENCES public.family( id );


create user bazingaadmin with password 'N&*Ohasd23rtv 3';

grant all privileges on all tables in  schema public to bazingaadmin; 
