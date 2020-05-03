CREATE TABLE example (
                         id					integer 	PRIMARY KEY,
                         mlii				integer,
                         v5					integer,
                         example_time 	integer 		NOT NULL,
                         type_of_anomaly_id integer 	NOT NULL,
                         sub 				integer		DEFAULT 0,
                         chan				integer		DEFAULT 0,
                         num				integer		DEFAULT 0,
                         letter             char        NOT NULL ,
                         result_id 			integer
);

CREATE TABLE type_of_anomaly
(
    id integer PRIMARY KEY,
    type_of_anomaly text UNIQUE NOT NULL);


CREATE TABLE tact (
    id              integer     PRIMARY KEY ,
    start_time      integer        NOT NULL,
    end_time        integer        NOT NULL,
    tact_string     text        NOT NULL,
    result_id 	    integer);

CREATE TABLE result (
                        id					integer 	PRIMARY KEY,
                        result_string		text	NOT NULL,
                        anomaly 			integer,
                        person_id 			integer);


CREATE TABLE anomaly (
                           id				integer 	PRIMARY KEY,
                           anomaly_string	text --10-20
);

CREATE TABLE person (
                       id					integer 	PRIMARY KEY,
                       age					integer,
                       weight				integer,
                       height				integer,
                       sex					integer
);

ALTER TABLE example
    ADD CONSTRAINT fk_result FOREIGN KEY (result_id) REFERENCES result (id);
;

ALTER TABLE result
    ADD CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person (id);
;

ALTER TABLE example
    ADD CONSTRAINT fk_type_of_anomaly FOREIGN KEY (type_of_anomaly_id) REFERENCES type_of_anomaly (id);
;

ALTER TABLE tact
    ADD CONSTRAINT fk_result FOREIGN KEY (result_id) REFERENCES result (id);
;

INSERT INTO type_of_anomaly
VALUES  (0,''),
  (1,'+'),
  (2,'N'),
  (3,'A');




