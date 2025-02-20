DROP TABLE IF EXISTS sample;

CREATE TABLE sample (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL
);

INSERT INTO sample (description) VALUES ('USA');
INSERT INTO sample (description) VALUES ('France');
INSERT INTO sample (description) VALUES ('Brazil');
INSERT INTO sample (description) VALUES ('Italy');
INSERT INTO sample (description) VALUES ('Canada');
COMMIT;