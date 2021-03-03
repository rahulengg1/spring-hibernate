# CompositeKey

Composite key, or composite primary key, refers to cases where more than one column is used to specify the primary key of a table. 
Composite key implementation can be done using two way.

1. Using separate class to define composite key and use that class in main Entity class as a reference using @EmbeddedId
2. Use @IdClass annotation and pass composite key class. Customer and CustomerId represent this implementation.


```
CREATE TABLE customer (
email_id varchar(255) NOT NULL,
id int(11) NOT NULL,
name varchar(255) DEFAULT NULL,
PRIMARY KEY (email_id,id)
) ;

CREATE TABLE student (
email_id varchar(255) NOT NULL,
id int(11) NOT NULL,
name varchar(255) DEFAULT NULL,
PRIMARY KEY (`email_id,id)
) ;
```
