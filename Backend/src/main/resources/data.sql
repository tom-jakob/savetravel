INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(id, username, email, password) VALUES(777, 'testuser', 'testuser@test.de', '$2a$10$tmPQGqC7XxNmFKwfufGybOxb7HIDA.0lEOtb3ejAiiBn4EfoxI9TK');
INSERT INTO user_roles(user_id, role_id) VALUES(777, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(777, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(777, 3);