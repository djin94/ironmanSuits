CREATE TABLE weapons (
	id SERIAL PRIMARY KEY,
	capacity_ammo INTEGER NOT NULL
);

CREATE TABLE ammo (
	id SERIAL PRIMARY KEY,
	amount INTEGER NOT NULL,
	weapons_id INTEGER REFERENCES weapons(id)
);

CREATE TABLE Suits (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	is_developed BOOLEAN,
	weapons_id INTEGER NOT NULL REFERENCES weapons(id)
); 

CREATE TABLE Suit_parts (
	id SERIAL PRIMARY KEY,
	name VARCHAR(1000) NOT NULL
);

CREATE TABLE Suits_suit_parts (
	id SERIAL PRIMARY KEY,
	suits_id INTEGER REFERENCES suits(id),
	suit_parts_id INTEGER REFERENCES Suit_parts(id)
);