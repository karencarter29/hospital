

CREATE TABLE appointment (
                             shift_id BYTES(255) NOT NULL,
                             patient_id BYTES(255),
) PRIMARY KEY(shift_id);



CREATE TABLE patient (
                         id BYTES(255) NOT NULL,
                         date_of_birth DATE,
                         first_name STRING(255),
                         gender STRING(255),
                         last_name STRING(255),
                         phone_number STRING(255),
) PRIMARY KEY(id);


CREATE TABLE shift (
                       id BYTES(255) NOT NULL,
                       date DATE,
                       doctor_id BYTES(255),
                       end_time TIMESTAMP,
                       procedure_name STRING(255),
                       speciality_name STRING(255),
                       start_time TIMESTAMP,
) PRIMARY KEY(id);

ALTER TABLE appointment ADD CONSTRAINT FK4apif2ewfyf14077ichee8g06 FOREIGN KEY(patient_id) REFERENCES patient(id);

ALTER TABLE appointment ADD CONSTRAINT FKr3dgxvrvoonmuaopg4w8ifugs FOREIGN KEY(shift_id) REFERENCES shift(id);

CREATE INDEX shift_doctorId_idx ON shift(doctor_id);

CREATE INDEX appointment_patientId_idx ON appointment(patient_id);

CREATE INDEX appointment_shiftId_idx ON appointment(shift_id);