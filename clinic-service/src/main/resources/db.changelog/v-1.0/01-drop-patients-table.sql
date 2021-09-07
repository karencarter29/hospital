
ALTER TABLE room
DROP CONSTRAINT  FKm7unmvas79cm9ry7ffds1rxv9;

ALTER TABLE room
DROP CONSTRAINT  FKsc1y77urc7knjy3bf7ssr46bk;

DROP INDEX UK_g7q5b45jwv4q15js8msilmii1;
DROP INDEX procedure_id_idx;
DROP INDEX room_doctorId_idx;
DROP INDEX room_hospitalId_idx;


drop table procedure;
drop table room;
drop table doctor;
drop table speciality;
drop table person;
drop table hospital;
