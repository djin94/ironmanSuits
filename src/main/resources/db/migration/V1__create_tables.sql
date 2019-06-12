CREATE TABLE weapons (
	id SERIAL PRIMARY KEY,
	name VARCHAR (255),
	capacity_ammo INTEGER NOT NULL
);

CREATE TABLE ammo (
	id SERIAL PRIMARY KEY,
	name VARCHAR (255) NOT NULL UNIQUE,
	amount INTEGER NOT NULL,
	weapons_id INTEGER UNIQUE REFERENCES weapons(id)
);

CREATE TABLE suits (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	is_developed BOOLEAN,
	weapons_id INTEGER REFERENCES weapons(id)
); 

CREATE TABLE suit_parts (
	id SERIAL PRIMARY KEY,
	name VARCHAR(1000) NOT NULL
);

CREATE TABLE suits_suit_parts (
	id SERIAL PRIMARY KEY,
	suits_id INTEGER REFERENCES suits(id),
	suit_parts_id INTEGER REFERENCES Suit_parts(id)
);