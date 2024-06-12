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
    Email varchar(320),
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
    constraint pk_bewirbt primary key (bewirbtID),
    constraint uk_bewirbt_StudentID unique (StudentID),
    constraint uk_bewirbt_StellenausschreibungID unique (StellenausschreibungID)
);

create table public.Interessen
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Interessen      varchar(256) not null,
    constraint pk_Interessen primary key (StudentID, laufende_nummer)
);

create table public.Kompetenzen
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Kompetenzen     varchar(256) not null,
    constraint pk_Kompetenzen primary key (StudentID, laufende_nummer)
);

create table public.Kontaktverknuepfung
(
    KontaktverknuepfungID       SERIAL not null,
    UnternehmenID               SERIAL not null,
    StudentID                   SERIAL not null,
    Bewertungsscore_Student     numeric(1),
    Beschreibung_Student        varchar(256),
    Bewertungsscore_Unternehmen numeric(1),
    Beschreibung_Unternehmen    varchar(256),
    constraint pk_Kontaktverknuepfung primary key (KontaktverknuepfungID),
    constraint uk_Kontaktverknuepfung_UnternehmenID unique (UnternehmenID),
    constraint uk_Kontaktverknuepfung_StudentID unique (StudentID)
);



create table public.Profil
(
    ProfilID              SERIAL not null,
    Avatar_URL            varchar(2048),
    Profilbeschreibung    varchar(6400),
    Xing_Benutzername     varchar(256),
    LinkedIn_Benutzername varchar(256),
    constraint pk_Profil primary key (ProfilID),
    constraint uk_Profil_Xing unique (Xing_Benutzername),
    constraint uk_Profil_LinkedIn unique (LinkedIn_Benutzername)
);

create table public.Stellenausschreibung
(
    StellenausschreibungID SERIAL        not null,
    UnternehmenID          SERIAL        not null,
    Titel                  varchar(256)  not null,
    Beschreibung           varchar(6400) not null,
    constraint pk_Stellenausschreibung primary key (StellenausschreibungID),
    constraint uk_Stellenausschreibung_UnternehmenID unique (UnternehmenID)
);

create table public.Student
(
    StudentID    SERIAL    not null,
    BenutzerID   SERIAL    not null,
    Nachname     char(128) not null,
    Geburtsdatum date,
    constraint pk_Student primary key (StudentID),
    constraint uk_Student_BenutzerID unique (BenutzerID)
);

create table public.Studiengang
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Studiengang     varchar(256) not null,
    constraint pk_Studiengang primary key (StudentID, laufende_nummer)
);

create table public.Unternehmen
(
    UnternehmenID SERIAL       not null,
    BenutzerID    SERIAL       not null,
    Name          varchar(128) not null,
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

create table public.Authority
(
    BenutzerID SERIAL not null,
    laufende_nummer numeric(2) not null,
    AuthorityRank varchar(16) not null,
    constraint pk_Authority primary key (BenutzerID, laufende_nummer)
);

create table public.PasswortResetToken
(
    PasswortResetTokenID SERIAL not null,
    BenutzerID SERIAL not null,
    Token varchar(256) not null,
    Ablaufdatum date not null,
    constraint pk_PasswortResetToken primary key (PasswortResetTokenID)
);


-- Constraints Section
-- ___________________


--    alter table collAtHbrs.Benutzer
--        add constraint chk_Benutzer_Unternehmen_BenutzerID
--            check (exists(select *
--                          from Unternehmen
--                          where Unternehmen.BenutzerID = BenutzerID));

--    alter table collAtHbrs.Benutzer
--        add constraint chk_Benutzer_Student_BenutzerID
--            check (exists(select *
--                          from Student
--                          where Student.BenutzerID = BenutzerID));

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

alter table public.Interessen
    add constraint fk_Interessen_Student
        foreign key (StudentID)
            references Student;

alter table public.Kompetenzen
    add constraint fk_Kompetenzen_Student
        foreign key (StudentID)
            references Student;

alter table public.Kontaktverknuepfung
    add constraint fk_Kontaktverknuepfung_Unternehmen
        foreign key (UnternehmenID)
            references Unternehmen;

alter table public.Kontaktverknuepfung
    add constraint fk_Kontaktverknuepfung_Student
        foreign key (StudentID)
            references Student;

alter table public.Authority
    add constraint fk_Authority_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.PasswortResetToken
    add constraint fk_PasswortResetToken_Benutzer
        foreign key (BenutzerID)
            references Benutzer;



--    alter table collAtHbrs.Profil
--        add constraint chk_Profil_Benutzer_ProfilID
--            check (exists(select *
--                          from Benutzer
--                          where Benutzer.ProfilID = ProfilID));

--    alter table collAtHbrs.Stellenausschreibung
--        add constraint chk_Stellenausschreibung_bewirbt_StellenausschreibungID
--            check (exists(select *
--                          from bewirbt
--                          where bewirbt.StellenausschreibungID = StellenausschreibungID));

alter table public.Stellenausschreibung
    add constraint fk_Stellenausschreibung_Unternehmen
        foreign key (UnternehmenID)
            references Unternehmen;

--    alter table collAtHbrs.Student
--        add constraint chk_Student_Interessen_StudentID
--            check (exists(select *
--                          from Interessen
--                          where Interessen.StudentID = StudentID));

--    alter table collAtHbrs.Student
--        add constraint chk_Student_Kompetenzen_StudentID
--            check (exists(select *
--                          from Kompetenzen
--                          where Kompetenzen.StudentID = StudentID));

--    alter table collAtHbrs.Student
--        add constraint chk_Student_Studiengang_StudentID
--            check (exists(select *
--                          from Studiengang
--                          where Studiengang.StudentID = StudentID));

--    alter table collAtHbrs.Student
--        add constraint chk_Student_Vorname_StudentID
--            check (exists(select *
--                          from Vorname
--                          where Vorname.StudentID = StudentID));

--    alter table collAtHbrs.Student
--        add constraint chk_Student_Kontaktverknuepfung_StudentID
--            check (exists(select *
--                          from Kontaktverknuepfung
--                          where Kontaktverknuepfung.StudentID = StudentID));

--    alter table collAtHbrs.Student
--        add constraint chk_Student_bewirbt_StudentID
--            check (exists(select *
--                          from bewirbt
--                          where bewirbt.StudentID = StudentID));

alter table public.Student
    add constraint fk_Student_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.Studiengang
    add constraint fk_Studiengang_Student
        foreign key (StudentID)
            references Student;

--    alter table collAtHbrs.Unternehmen
--        add constraint chk_Unternehmen_Kontaktverknuepfung_UnternehmenID
--            check (exists(select *
--                          from Kontaktverknuepfung
--                          where Kontaktverknuepfung.UnternehmenID = UnternehmenID));

alter table public.Unternehmen
    add constraint fk_Unternehmen_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.Vorname
    add constraint fk_Vorname_Student
        foreign key (StudentID)
            references Student;


-- Index Section
-- _____________

