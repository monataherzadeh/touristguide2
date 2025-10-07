DROP TABLE IF EXISTS person;

CREATE TABLE person (
                        id INT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(150),
                        tags,
                        location

);


-- init-data
INSERT INTO TouristAttraction (id, name, description, tags, location) VALUES (1, 'Joshua Tree Park', 'A lovely, but deserty attraction', ,'CA');
INSERT INTO TouristAttraction (id, name, description, tags, location) VALUES (2, 'Yellow Stone Nation Park', 'Yellow Stone Park is abundant i wildlife', List.of('Desert', 'Hiking') 'WY');
INSERT INTO TouristAttraction (id, name, description, tags, location) VALUES (3, 'Grand Canyon', 'Very steep sometimes',  , 'AZ');