#Component Mapping

When there is has a relationship within the table, component mapping can be used

For ex. A restaurant has a address , so there will be two different class
one for Restaurant and other for Address where Restaurant has a embedded reference to Address class.


```
CREATE TABLE restaurant( 
id bigint(20) NOT NULL AUTO_INCREMENT,
city varchar(255) DEFAULT NULL,
country varchar(255) DEFAULT NULL,
state varchar(255) DEFAULT NULL,
street_address varchar(255) DEFAULT NULL,
zip_code varchar(255) DEFAULT NULL,
name varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
);
```
