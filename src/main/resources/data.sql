INSERT INTO elector (id, name, surname, login, password, role, is_locked) VALUES
(1, 'Admin', 'Istrator', 'admin', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ADMIN', FALSE);

INSERT INTO elector (id, name, surname, login, password, role, is_locked) VALUES
(2, 'Anna', 'Kowalska', 'user.a', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(3, 'Piotr', 'Nowak', 'user.b', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(4, 'Katarzyna', 'Wiśniewska', 'user.c', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(5, 'Marcin', 'Wójcik', 'user.d', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(6, 'Magdalena', 'Kowalczyk', 'user.e', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(7, 'Tomasz', 'Kamiński', 'user.f', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(8, 'Ewa', 'Lewandowska', 'user.g', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', TRUE), -- LOCKED
(9, 'Jan', 'Zielinski', 'user.h', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', TRUE), -- LOCKED
(10, 'Aleksandra', 'Szymańska', 'user.i', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE),
(11, 'Michał', 'Woźniak', 'user.j', '$2a$10$EH7yMXG93zeZuIC.MSF/3OYHKFMeZ/N.yFXoFs2HV.nCbjOfOhQ7O', 'ELECTOR', FALSE);

INSERT INTO election (id, name, start_date_time, end_date_time) VALUES
(10, 'Wybory Rektora 2024', '2024-05-01 08:00:00', '2024-05-15 18:00:00');

INSERT INTO election (id, name, start_date_time, end_date_time) VALUES
(20, 'Wybory na Wójta w 2025', '2025-12-01 09:00:00', '2025-12-30 23:59:59');

INSERT INTO election (id, name, start_date_time, end_date_time) VALUES
(30, 'Najlepsza potrawa wigilijna', '2025-12-06 10:00:00', '2025-12-24 15:30:00');

INSERT INTO election (id, name, start_date_time, end_date_time) VALUES
(40, 'Wybory do Rady Wydziału', '2026-01-07 11:00:00', '2026-01-26 17:00:00');

INSERT INTO candidate (id, election_id, name) VALUES
(100, 10, 'Kandydat A'),
(101, 10, 'Kandydat B'),
(102, 10, 'Kandydat C');

INSERT INTO candidate (id, election_id, name)  VALUES
(200, 20, 'Paweł Kozioł'),
(201, 20, 'Lucy Wilska');

INSERT INTO candidate (id, election_id, name)  VALUES
(300, 30, 'Pierogi'),
(301, 30, 'Barszcz czerowny'),
(302, 30, 'Kompot z suszu');

INSERT INTO candidate (id, election_id, name)  VALUES
(400, 40, 'Jan Nowak - Radny'),
(401, 40, 'Ewa Kowalska - Radna');

INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (1, 1, 10, 100);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (2, 2, 10, 100);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (3, 3, 10, 101);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (4, 4, 10, 100);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (5, 5, 10, 102);

INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (6, 6, 20, 200);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (7, 7, 20, 200);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (8, 10, 20, 201);

INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (9, 11, 30, 300);
INSERT INTO vote (id, elector_id, election_id, candidate_id) VALUES (10, 1, 30, 301);


ALTER TABLE elector ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM elector);

ALTER TABLE election ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM election);

ALTER TABLE candidate ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM candidate);

ALTER TABLE vote ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM vote);
