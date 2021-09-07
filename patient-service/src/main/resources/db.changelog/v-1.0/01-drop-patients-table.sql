ALTER TABLE appointment
DROP CONSTRAINT  FK4apif2ewfyf14077ichee8g06;

ALTER TABLE appointment
DROP CONSTRAINT  FKr3dgxvrvoonmuaopg4w8ifugs;

DROP INDEX shift_doctorId_idx;
DROP INDEX appointment_patientId_idx;
DROP INDEX appointment_shiftId_idx;



drop table appointment;
drop table shift;
drop table patient;
