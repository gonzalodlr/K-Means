CREATE TABLE MapDB.people(
       genere varchar(1),
       nazionalita varchar(10),
       eta float(5,2)
);

insert into MapDB.people values('F','Italiana',25);
insert into MapDB.people values('F','Italiana',27);
insert into MapDB.people values('F','Italiana',34);
insert into MapDB.people values('F','Inglese',23);
insert into MapDB.people values('M','Americana',29);
commit;