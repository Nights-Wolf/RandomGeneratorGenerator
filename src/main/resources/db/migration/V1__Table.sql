CREATE TABLE Tag (
tag_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_name VARCHAR(1000),
CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);

INSERT INTO Tag (tag_name) VALUES ('NAME');
INSERT INTO Tag (tag_name) VALUES ('ITEM');
INSERT INTO Tag (tag_name) VALUES ('MALE');
INSERT INTO Tag (tag_name) VALUES ('FEMALE');
INSERT INTO Tag (tag_name) VALUES ('HUMAN');
INSERT INTO Tag (tag_name) VALUES ('HALFLING');
INSERT INTO Tag (tag_name) VALUES ('DWARF');
INSERT INTO Tag (tag_name) VALUES ('ELF');
INSERT INTO Tag (tag_name) VALUES ('HALF-ELF');
INSERT INTO Tag (tag_name) VALUES ('GNOME');
INSERT INTO Tag (tag_name) VALUES ('ORC');
INSERT INTO Tag (tag_name) VALUES ('HALF-ORC');
INSERT INTO Tag (tag_name) VALUES ('TIEFLING');
INSERT INTO Tag (tag_name) VALUES ('DRAGONBORN');
INSERT INTO Tag (tag_name) VALUES ('ACHTFACH');
INSERT INTO Tag (tag_name) VALUES ('IBRESIA');
INSERT INTO Tag (tag_name) VALUES ('MERSEAUX');

CREATE TABLE Name (
name_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
name_name VARCHAR(1000),
CONSTRAINT name_pk PRIMARY KEY (name_id)
);

CREATE TABLE Content (
content_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_id INTEGER,
name_id INTEGER,
CONSTRAINT content_pk PRIMARY KEY (content_id),
CONSTRAINT content_fk1 FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
CONSTRAINT content_fk2 FOREIGN KEY (name_id) REFERENCES name(name_id)
);

CREATE TABLE KitsName (
kitsName_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
kitsName_name VARCHAR(1000),
CONSTRAINT kitsName_pk PRIMARY KEY (kitsName_id)
);

CREATE TABLE KitsContent (
kitsContent_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
kitsName_id INTEGER,
name_id INTEGER,
CONSTRAINT kitsContent_pk PRIMARY KEY (kitsContent_id),
CONSTRAINT kitsContent_fk1 FOREIGN KEY (kitsName_id) REFERENCES KitsName(kitsName_id),
CONSTRAINT kitsContent_fk2 FOREIGN KEY (name_id) REFERENCES Name(name_id)
);

CREATE TABLE Used (
used_Tags_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
name_id INTEGER,
CONSTRAINT used_pk PRIMARY KEY (used_Tags_id),
CONSTRAINT used_fk FOREIGN KEY (name_id) REFERENCES Name(name_id)
);