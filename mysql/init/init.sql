CREATE DATABASE cherishdata;
CREATE user cherishpet@localhost identified by 'thddlcofla';
GRANT ALL PRIVILEGES ON cherishdata.* TO 'cherishpet'@'%';
SET PASSWORD FOR 'cherishpet'@'localhost' = 'thddlcofla';

USE cherishdata;
