-- DROP TABLE IF EXISTS names;
CREATE TABLE  names (
  id bigint(20) NOT NULL auto_increment,
  name varchar(45) NOT NULL,
  PRIMARY KEY  (id),
  CONSTRAINT unique_name UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
