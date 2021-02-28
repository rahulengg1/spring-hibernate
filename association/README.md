# Association

Association basically means relationship among the tables or how the tables associated each other by Primary key and foreign key.

JPA supports four types of association

1. @OneToOne
2. @OneToMany
3. @ManyToMany
4. @ManyToOne


@ManyToMany

```
CREATE TABLE programmer (
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(255) DEFAULT NULL,
salary int(11) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE programmer_projects (
  programmer_id int(11) NOT NULL,
  project_id int(11) NOT NULL,
  PRIMARY KEY (programmer_id,project_id),
  KEY FK_Programmer_project (project_id),
  CONSTRAINT FK_Programmer FOREIGN KEY (programmer_id) REFERENCES programmer (id),
  CONSTRAINT FK_Programmer_project FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE project (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
```


@OneToMany

```
CREATE TABLE supplier (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE phone_number (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  phone_number varchar(255) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  supplier_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_supplier (supplier_id),
  CONSTRAINT FK_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (id)
) ;


```

@OneToOne

```
CREATE TABLE license (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type varchar(255) DEFAULT NULL,
  valid_from date DEFAULT NULL,
  valid_to date DEFAULT NULL,
  person_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_person (person_id),
  CONSTRAINT FK_person FOREIGN KEY (person_id) REFERENCES person (id)
) ;

CREATE TABLE person (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ;
```