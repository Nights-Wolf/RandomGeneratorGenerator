CREATE TABLE Tag (
tag_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_name VARCHAR(20),
CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);

CREATE TABLE Name (
name_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
name_name VARCHAR(20),
CONSTRAINT name_pk PRIMARY KEY (name_id)
);

INSERT INTO Name (name_name) VALUES ('Dawid');
INSERT INTO Name (name_name) VALUES ('Artur');
INSERT INTO Name (name_name) VALUES ('Kamila');
INSERT INTO Name (name_name) VALUES ('Kuba');
INSERT INTO Name (name_name) VALUES ('Julian');
INSERT INTO Name (name_name) VALUES ('Agata');
INSERT INTO Name (name_name) VALUES ('Dominika');
INSERT INTO Name (name_name) VALUES ('Kamil');
INSERT INTO Name (name_name) VALUES ('Damian');
INSERT INTO Name (name_name) VALUES ('Karol');
INSERT INTO Name (name_name) VALUES ('Julia');
INSERT INTO Name (name_name) VALUES ('Zuza');
INSERT INTO Name (name_name) VALUES ('Grześ');

CREATE TABLE Content (
content_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
tag_id INTEGER,
name_id INTEGER,
CONSTRAINT content_pk PRIMARY KEY (content_id),
CONSTRAINT content_fk1 FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
CONSTRAINT content_fk2 FOREIGN KEY (name_id) REFERENCES name(name_id)
);