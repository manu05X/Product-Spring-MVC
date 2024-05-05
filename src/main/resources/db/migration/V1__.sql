CREATE TABLE category
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE category_seq
(
    next_val BIGINT NULL
);

CREATE TABLE jt_mentor
(
    rating INT    NOT NULL,
    id     BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_student
(
    batch VARCHAR(255) NULL,
    psp   VARCHAR(255) NULL,
    id    BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_user
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE jt_user_seq
(
    next_val BIGINT NULL
);

CREATE TABLE mentor
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    rating   INT    NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE mentor_seq
(
    next_val BIGINT NULL
);

CREATE TABLE product
(
    id            BIGINT NOT NULL,
    `description` VARCHAR(255) NULL,
    price         BIGINT NULL,
    title         VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE product_seq
(
    next_val BIGINT NULL
);

CREATE TABLE st_user
(
    user_type INT    NOT NULL,
    id        BIGINT NOT NULL,
    email     VARCHAR(255) NULL,
    name      VARCHAR(255) NULL,
    password  VARCHAR(255) NULL,
    rating    INT NULL,
    batch     VARCHAR(255) NULL,
    psp       VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE st_user_seq
(
    next_val BIGINT NULL
);

CREATE TABLE student
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    psp      VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE student_seq
(
    next_val BIGINT NULL
);

CREATE TABLE tpc_mentor
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    rating   INT    NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tpc_student
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    psp      VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    name     VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE tpc_user_seq
(
    next_val BIGINT NULL
);

ALTER TABLE product
    ADD CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK3b8n8r2bqpqf7bj218e3ch9q6 FOREIGN KEY (id) REFERENCES jt_user (id) ON DELETE NO ACTION;

ALTER TABLE jt_student
    ADD CONSTRAINT FKcdd1n57kqnhoqnlgi7tpfhndy FOREIGN KEY (id) REFERENCES jt_user (id) ON DELETE NO ACTION;