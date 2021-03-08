CREATE TABLE Tag (
tag_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_name VARCHAR(50),
CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);

INSERT INTO Tag (tag_name) VALUES ('Name');
INSERT INTO Tag (tag_name) VALUES ('Item');
INSERT INTO Tag (tag_name) VALUES ('Male');
INSERT INTO Tag (tag_name) VALUES ('Female');
INSERT INTO Tag (tag_name) VALUES ('Human');
INSERT INTO Tag (tag_name) VALUES ('Achtfanian');
INSERT INTO Tag (tag_name) VALUES ('MY');

CREATE TABLE Name (
name_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
name_name VARCHAR(50),
category VARCHAR(50),
CONSTRAINT name_pk PRIMARY KEY (name_id)
);

INSERT INTO Name (name_name, category) VALUES ('Dawid', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Artur', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Kamila', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Kuba', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Julian', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Agata', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Dominika', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Kamil', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Damian', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Karol', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Julia', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Zuza', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Grześ', 'Name');
INSERT INTO Name (name_name, category) VALUES ('Magic Poison', 'Item');
INSERT INTO Name (name_name, category) VALUES ('Wand', 'Item');
INSERT INTO Name (name_name, category) VALUES ('Sword', 'Item');
INSERT INTO Name (name_name, category) VALUES ('Wunderbachtenfreunde', 'Item');


CREATE TABLE Content (
content_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_id INTEGER,
name_id INTEGER,
CONSTRAINT content_pk PRIMARY KEY (content_id),
CONSTRAINT content_fk1 FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
CONSTRAINT content_fk2 FOREIGN KEY (name_id) REFERENCES name(name_id)
);

INSERT INTO Content(tag_id, name_id) VALUES (1, 1);
INSERT INTO Content(tag_id, name_id) VALUES (1, 2);
INSERT INTO Content(tag_id, name_id) VALUES (1, 3);
INSERT INTO Content(tag_id, name_id) VALUES (1, 4);
INSERT INTO Content(tag_id, name_id) VALUES (1, 5);
INSERT INTO Content(tag_id, name_id) VALUES (1, 6);
INSERT INTO Content(tag_id, name_id) VALUES (1, 7);
INSERT INTO Content(tag_id, name_id) VALUES (1, 8);
INSERT INTO Content(tag_id, name_id) VALUES (1, 9);
INSERT INTO Content(tag_id, name_id) VALUES (1, 10);
INSERT INTO Content(tag_id, name_id) VALUES (1, 11);
INSERT INTO Content(tag_id, name_id) VALUES (1, 12);
INSERT INTO Content(tag_id, name_id) VALUES (1, 13);
INSERT INTO Content(tag_id, name_id) VALUES (2, 14);
INSERT INTO Content(tag_id, name_id) VALUES (2, 15);
INSERT INTO Content(tag_id, name_id) VALUES (2, 16);
INSERT INTO Content(tag_id, name_id) VALUES (2, 17);
INSERT INTO Content(tag_id, name_id) VALUES (3, 1);
INSERT INTO Content(tag_id, name_id) VALUES (3, 2);
INSERT INTO Content(tag_id, name_id) VALUES (4, 3);
INSERT INTO Content(tag_id, name_id) VALUES (4, 7);
INSERT INTO Content(tag_id, name_id) VALUES (6, 1);
INSERT INTO Content(tag_id, name_id) VALUES (6, 2);
INSERT INTO Content(tag_id, name_id) VALUES (6, 3);
INSERT INTO Content(tag_id, name_id) VALUES (6, 7);
INSERT INTO Content(tag_id, name_id) VALUES (7, 1);
INSERT INTO Content(tag_id, name_id) VALUES (7, 5);
INSERT INTO Content(tag_id, name_id) VALUES (7, 3);
INSERT INTO Content(tag_id, name_id) VALUES (5, 1);
INSERT INTO Content(tag_id, name_id) VALUES (5, 2);
INSERT INTO Content(tag_id, name_id) VALUES (5, 3);
INSERT INTO Content(tag_id, name_id) VALUES (5, 4);
INSERT INTO Content(tag_id, name_id) VALUES (5, 5);
INSERT INTO Content(tag_id, name_id) VALUES (5, 6);
INSERT INTO Content(tag_id, name_id) VALUES (5, 7);
INSERT INTO Content(tag_id, name_id) VALUES (5, 8);
INSERT INTO Content(tag_id, name_id) VALUES (5, 9);
INSERT INTO Content(tag_id, name_id) VALUES (5, 10);
INSERT INTO Content(tag_id, name_id) VALUES (5, 11);
INSERT INTO Content(tag_id, name_id) VALUES (5, 12);
INSERT INTO Content(tag_id, name_id) VALUES (5, 13);

CREATE TABLE KitsName (
kitsName_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
kitsName_name VARCHAR(20),
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