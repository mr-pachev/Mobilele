INSERT INTO brands(name) VALUES("Opel");

INSERT INTO brands(name) VALUES("Skoda");

INSERT INTO brands(name) VALUES("Reno");

INSERT INTO models(category, name, brand_id) VALUES("Car", "Zafira", "1");
INSERT INTO models(category, name, brand_id) VALUES("Car", "Astra G", "1");
INSERT INTO models(category, name, brand_id) VALUES("Car", "Corsa", "1");

INSERT INTO models(category, name, brand_id) VALUES("Car", "Rapid", "2");
INSERT INTO models(category, name, brand_id) VALUES("Car", "Octavia", "2");
INSERT INTO models(category, name, brand_id) VALUES("Car", "Superb", "2");

INSERT INTO models(category, name, brand_id) VALUES("Car", "Clio", "3");
INSERT INTO models(category, name, brand_id) VALUES("Car", "19", "3");
INSERT INTO models(category, name, brand_id) VALUES("Car", "Captur", "3");

INSERT INTO roles(role) VALUES("User");
INSERT INTO roles(role) VALUES("Admin");

INSERT INTO users(email, first_name, last_name, password, username, role_id) VALUES("iko@abv.bg", "Ivan", "Pachev", "1234", "iunak","2");
INSERT INTO users(email, first_name, last_name, password, username, role_id) VALUES("mitka@mail.bg", "Dimitrinka", "Mitova", "5678", "mitka","1");
INSERT INTO users(email, first_name, last_name, password, username, role_id) VALUES("penio@abv.bg", "Pencho", "Ginin", "6789", "penata","1");