-- Settings and Test Section
-- _______________
CREATE SCHEMA IF NOT EXISTS public;

-- Database Section
-- _______________


-- create database collAtHbrs;


-- DBSpace Section
-- _______________


-- Tables Section
-- _____________

create table public.Benutzer
(
    BenutzerID  SERIAL      not null,
    ProfilID    SERIAL      not null,
    Username    varchar(32) not null,
    Passwort    varchar(64) not null,
    Blacklisted numeric(1),
    Email       varchar(320),
    constraint uk_Benutzer_Username unique (Username),
    constraint uk_Benutzer_Email unique (Email),
    constraint pk_Benutzer primary key (BenutzerID),
    constraint uk_Benutzer_ProfilID unique (ProfilID)
);

create table public.bewirbt
(
    bewirbtID              SERIAL not null,
    StudentID              SERIAL not null,
    StellenausschreibungID SERIAL not null,
    Anschreiben            TEXT,
    constraint pk_bewirbt primary key (bewirbtID)
);

create table public.Profil
(
    ProfilID              SERIAL not null,
    Avatar                TEXT,
    Profilbeschreibung    varchar(6400),
    Xing_Benutzername     varchar(256),
    LinkedIn_Benutzername varchar(256),
    constraint pk_Profil primary key (ProfilID),
    constraint uk_Profil_Xing unique (Xing_Benutzername),
    constraint uk_Profil_LinkedIn unique (LinkedIn_Benutzername)
);

create table public.Stellenausschreibung
(
    StellenausschreibungID  SERIAL        not null,
    UnternehmenID           SERIAL        not null,
    Titel                   varchar(256)  not null,
    Beschreibung            varchar(6400) not null,
    Anstellungsart          varchar(128)  not null,
    Standort                varchar(256)  not null,
    Veroeffentlichungsdatum date,
    constraint pk_Stellenausschreibung primary key (StellenausschreibungID),
    constraint uk_Stellenausschreibung_UnternehmenID unique (UnternehmenID)
);

create table public.Student
(
    StudentID    SERIAL    not null,
    BenutzerID   SERIAL    not null,
    Nachname     char(128) not null,
    Geburtsdatum date,
    Lebenslauf   TEXT,
    constraint pk_Student primary key (StudentID),
    constraint uk_Student_BenutzerID unique (BenutzerID)
);

create table public.Unternehmen
(
    UnternehmenID SERIAL       not null,
    BenutzerID    SERIAL       not null,
    Name          varchar(128) not null,
    Adresse       varchar(128),
    Stadt         varchar(128),
    Postleitzahl  varchar(8),
    Land          varchar(128),
    constraint pk_Unternehmen primary key (UnternehmenID),
    constraint uk_Unternehmen_BenutzerID unique (BenutzerID)
);

create table public.Vorname
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Vorname         varchar(128) not null,
    constraint pk_Vorname primary key (StudentID, laufende_nummer)
);

create table public.Anforderungen
(
    StellenausschreibungID SERIAL       not null,
    laufende_nummer        numeric(2)   not null,
    Anforderungen          varchar(128) not null,
    constraint pk_Anforderungen primary key (StellenausschreibungID, laufende_nummer)
);

create table public.Aufgaben
(
    StellenausschreibungID SERIAL       not null,
    laufende_nummer        numeric(2)   not null,
    Aufgaben               varchar(128) not null,
    constraint pk_Aufgaben primary key (StellenausschreibungID, laufende_nummer)
);


-- Constraints Section
-- ___________________

alter table public.Benutzer
    add constraint fk_Benutzer_Profil
        foreign key (ProfilID)
            references Profil;

alter table public.bewirbt
    add constraint fk_bewirbt_Student
        foreign key (StudentID)
            references Student;

alter table public.bewirbt
    add constraint fk_bewirbt_Stellenausschreibung
        foreign key (StellenausschreibungID)
            references Stellenausschreibung;

alter table public.Stellenausschreibung
    add constraint fk_Stellenausschreibung_Unternehmen
        foreign key (UnternehmenID)
            references Unternehmen;


alter table public.Student
    add constraint fk_Student_Benutzer
        foreign key (BenutzerID)
            references Benutzer;


alter table public.Unternehmen
    add constraint fk_Unternehmen_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.Vorname
    add constraint fk_Vorname_Student
        foreign key (StudentID)
            references Student;

alter table public.Anforderungen
    add constraint fk_Anforderungen_Stellenausschreibung
        foreign key (StellenausschreibungID)
            references Stellenausschreibung;

alter table public.Aufgaben
    add constraint fk_Aufgaben_Stellenausschreibung
        foreign key (StellenausschreibungID)
            references Stellenausschreibung;

-- Index Section
-- _____________

