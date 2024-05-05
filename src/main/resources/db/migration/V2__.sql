ALTER TABLE product
    ADD rating INT NULL;

ALTER TABLE product
    MODIFY rating INT NOT NULL;

ALTER TABLE st_user
    MODIFY rating INT NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;