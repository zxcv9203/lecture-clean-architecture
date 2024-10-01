INSERT INTO users (name)
VALUES ('user1');
INSERT INTO users (name)
VALUES ('user2');
INSERT INTO users (name)
VALUES ('user3');

INSERT INTO lectures (name, lecturer, enrollment_count, start_time, end_time)
VALUES ('lecture1', 'lecturer-1', 2,'2021-01-01 00:00:00', '2021-01-01 01:00:00');
INSERT INTO lectures (name, lecturer, enrollment_count, start_time, end_time)
VALUES ('lecture2', 'lecturer-2', 1, '2021-01-01 00:00:00', '2021-01-01 01:00:00');

INSERT INTO user_lectures (user_id, lecture_id)
VALUES (1, 1);
INSERT INTO user_lectures (user_id, lecture_id)
VALUES (2, 1);
INSERT INTO user_lectures (user_id, lecture_id)
VALUES (1, 2);
