CREATE TABLE patient (
                         id BYTES(255) NOT NULL,
                         date_of_birth DATE,
                         gender STRING(255),
                         phone_number STRING(255),
                         user_id INT64 NOT NULL,
) PRIMARY KEY(id);


CREATE TABLE shift (
                       id BYTES(255) NOT NULL,
                       date DATE,
                       end_time TIMESTAMP,
                       procedure_name STRING(255),
                       speciality_name STRING(255),
                       start_time TIMESTAMP,
) PRIMARY KEY(id);



CREATE TABLE appointment (
                             shift_id BYTES(255) NOT NULL,
                             patient_id BYTES(255) NOT NULL,
) PRIMARY KEY(patient_id, shift_id);

CREATE UNIQUE INDEX UK_hceh1f14irmc0x37f7bfulmo2 ON appointment(shift_id);

ALTER TABLE appointment ADD CONSTRAINT FK4apif2ewfyf14077ichee8g06 FOREIGN KEY(patient_id) REFERENCES patient(id);
ALTER TABLE appointment ADD CONSTRAINT FKr3dgxvrvoonmuaopg4w8ifugs FOREIGN KEY(shift_id) REFERENCES shift(id);
