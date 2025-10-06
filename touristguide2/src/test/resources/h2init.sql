DROP TABLE IF EXISTS person;

CREATE TABLE person (
                        id INT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(150),
                        tags,
                        location

);


-- init-data
INSERT INTO person (id, name, description, tags, location) VALUES (1, 'Joshua Tree Park', 'A lovely, but deserty attraction', ,'CA');
INSERT INTO person (id, name, description, tags, location) VALUES (1, 'Yellow Stone Nation Park', 'Yellow Stone Park is abundant i wildlife', 'WY');
INSERT INTO person (id, name, description, tags, location) VALUES (1, 'Grand Canoyon', 'Very steep sometimes',  , 'AZ');