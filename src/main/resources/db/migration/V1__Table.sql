CREATE TABLE firstTable (
id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
gool VARCHAR(20),
wol VARCHAR(20),
hul VARCHAR(20)
);

INSERT INTO firstTable(gool, wol, hul) VALUES('lol', 'kul', 'jul');
INSERT INTO firstTable(gool, wol, hul) VALUES('lol', 'kul', 'jul');