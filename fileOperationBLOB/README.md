# FileOperation BLOB

Basically this tutorial provides a basic usage of BLOB to store file into Database and retrieve the file from database.

Please check the test class to find the example how to use BLOB.

```
CREATE TABLE file_entity (
id bigint(20) NOT NULL AUTO_INCREMENT,
file_content longblob,
file_name varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
);
```