-- Settings and Test Section
-- _______________
CREATE SCHEMA IF NOT EXISTS public;

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
    constraint pk_bewirbt primary key (bewirbtID),
    constraint uk_bewirbt_StudentID unique (StudentID),
    constraint uk_bewirbt_StellenausschreibungID unique (StellenausschreibungID)
);

create table public.Interessen
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Interessen      varchar(256) not null,
    constraint pk_Interessen primary key (StudentID, laufende_nummer),
    constraint uk_Interessen_StudentID unique (StudentID)
);

create table public.Kompetenzen
(
    StudentID       SERIAL       not null,
    laufende_nummer numeric(2)   not null,
    Kompetenzen     varchar(256) not null,
    constraint pk_Kompetenzen primary key (StudentID, laufende_nummer),
    constraint uk_Kompetenzen_StudentID unique (StudentID)
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
    constraint pk_Studiengang primary key (StudentID, laufende_nummer),
    constraint uk_Studiengang_StudentID unique (StudentID)
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
    constraint pk_Vorname primary key (StudentID, laufende_nummer),
    constraint uk_Vorname_StudentID unique (StudentID)
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

alter table public.Stellenausschreibung
    add constraint fk_Stellenausschreibung_Unternehmen
        foreign key (UnternehmenID)
            references Unternehmen;

alter table public.Student
    add constraint fk_Student_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.Studiengang
    add constraint fk_Studiengang_Student
        foreign key (StudentID)
            references Student;

alter table public.Unternehmen
    add constraint fk_Unternehmen_Benutzer
        foreign key (BenutzerID)
            references Benutzer;

alter table public.Vorname
    add constraint fk_Vorname_Student
        foreign key (StudentID)
            references Student;
