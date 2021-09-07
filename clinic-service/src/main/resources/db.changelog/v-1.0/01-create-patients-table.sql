CREATE TABLE doctor (
                        id BYTES(255) NOT NULL,
                        phone_number STRING(255),
                        user_id INT64 NOT NULL,
                        speciality_id BYTES(255),
) PRIMARY KEY(id);

CREATE TABLE hospital (
                          id BYTES(255) NOT NULL,
                          address STRING(255),
                          name STRING(255),
) PRIMARY KEY(id);

CREATE TABLE person (
                        id BYTES(255) NOT NULL,
                        first_name STRING(255),
                        password STRING(255),
                        role_id INT64 NOT NULL,
                        second_name STRING(255),
                        user_name STRING(255),
) PRIMARY KEY(id);

CREATE TABLE procedure (
                           id BYTES(255) NOT NULL,
                           procedure_name STRING(255),
                           speciality_id BYTES(255),
) PRIMARY KEY(id);

CREATE TABLE room (
                      room_number STRING(255),
                      hospital_id BYTES(255) NOT NULL,
                      doctor_id BYTES(255) NOT NULL,
                      CONSTRAINT FKm7unmvas79cm9ry7ffds1rxv9 FOREIGN KEY(doctor_id) REFERENCES doctor(id),
                      CONSTRAINT FKsc1y77urc7knjy3bf7ssr46bk FOREIGN KEY(hospital_id) REFERENCES hospital(id),
) PRIMARY KEY(doctor_id, hospital_id);

CREATE UNIQUE INDEX UK_g7q5b45jwv4q15js8msilmii1 ON room(doctor_id);

CREATE TABLE speciality (
                            id BYTES(255) NOT NULL,
                            speciality_name STRING(255),
) PRIMARY KEY(id);

ALTER TABLE procedure ADD CONSTRAINT FK56rucj7tofhe97iqjt1khyr1u FOREIGN KEY(speciality_id) REFERENCES speciality(id);

ALTER TABLE doctor ADD CONSTRAINT FKdht1k3ou6ody790hip20bl9dx FOREIGN KEY(speciality_id) REFERENCES speciality(id)