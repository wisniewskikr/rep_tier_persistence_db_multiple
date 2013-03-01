-- DROP TABLE IF EXISTS surnames;
CREATE TABLE  surnames (
  id bigint(20) NOT NULL auto_increment,
  surname varchar(45) NOT NULL,
  PRIMARY KEY  (id),
  CONSTRAINT unique_surname UNIQUE (surname)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

