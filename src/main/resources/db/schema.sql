CREATE TABLE IF NOT EXISTS `users`
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL COMMENT '사용자 이름'
);

CREATE TABLE IF NOT EXISTS `lectures`
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(255) COMMENT '특강 이름',
    lecturer         VARCHAR(50) COMMENT '특강 강사 이름',
    enrollment_count INT COMMENT '특강 수강 인원',
    start_time       DATETIME COMMENT '특강 시작 시간',
    end_time         DATETIME COMMENT '특강 종료 시간'
);

CREATE TABLE IF NOT EXISTS `user_lectures`
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT NOT NULL COMMENT '사용자 ID',
    lecture_id BIGINT COMMENT '특강 ID',

    CONSTRAINT fk_user_lectures_users_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_lectures_lectures_id FOREIGN KEY (lecture_id) REFERENCES lectures (id)
);

ALTER TABLE user_lectures
    ADD CONSTRAINT unique_user_lecture UNIQUE (user_id, lecture_id);
