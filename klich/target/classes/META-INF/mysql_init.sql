
INSERT INTO TUSER(USER_ID, LOGIN, PASSWORD, ACTIVE, EMAIL, CREATION_DATE, RECOVERY, PHONE, NAME, IDX)
  VALUES(-1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 'admin@admin', NULL, NULL, NULL, NULL, NULL);
  
INSERT INTO TROLE (role_id, LKEY) values(1,'ROLE_ADMIN');
INSERT INTO TROLE (role_id, LKEY) values(2,'ROLE_USER');
INSERT INTO TROLE (role_id, LKEY) values(3,'ROLE_SUPERVISOR_SSCC');
INSERT INTO TROLE (role_id, LKEY) values(4,'ROLE_SUPERVISOR');
INSERT INTO TROLE (role_id, LKEY) values(5,'ROLE_INSPECTOR');
INSERT INTO TROLE (role_id, LKEY) values(6,'ROLE_ENTREVISTADOR');
INSERT INTO TROLE (role_id, LKEY) values(7,'ROLE_CATI');
INSERT INTO TROLE (role_id, LKEY) values(8,'ROLE_CAPI');
INSERT INTO TROLE (role_id, LKEY) values(29,'ROLE_TESTER');

-- Permisos para admin
insert into TUSER_TROLE (TUSER_TROLE_id,user_id, role_id) values(-1,-1, 1);
insert into TUSER_TROLE (TUSER_TROLE_id,user_id, role_id) values(-2,-1, 2);
insert into TUSER_TROLE (TUSER_TROLE_id,user_id, role_id) values(-3,-1, 3);
insert into TUSER_TROLE (TUSER_TROLE_id,user_id, role_id) values(-4,-1, 7);
insert into TUSER_TROLE (TUSER_TROLE_id,user_id, role_id) values(-5,-1, 8);

-- STATUS
INSERT INTO status(STATUS_ID, SHORT_DESCRIPTION, DESCRIPTION,INTERNAL,FINAL)
 VALUES  (5,'','',1,0);
INSERT INTO status(STATUS_ID, SHORT_DESCRIPTION, DESCRIPTION,INTERNAL,FINAL)
 VALUES  (1,'GPS','GPS',0,0);
INSERT INTO status(STATUS_ID, SHORT_DESCRIPTION, DESCRIPTION,INTERNAL,FINAL)
 VALUES  (2,'NET','GOOGLE NETWORK',0,0);
INSERT INTO status(STATUS_ID, SHORT_DESCRIPTION, DESCRIPTION,INTERNAL,FINAL)
 VALUES  (3,'ANT','ANTENAS DE TELEFONIA MOVIL',0,0);
INSERT INTO status(STATUS_ID, SHORT_DESCRIPTION, DESCRIPTION,INTERNAL,FINAL)
 VALUES  (4,'SHY','SHYHOOK',0,0);
 
-- DEVICE
INSERT INTO Device(DEVICE_ID, BRAND, MODEL,TYPE,USER_ID)
 VALUES  (1,'Samsung','Galaxy S2', 'Smartphone',-1);

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 



  
  
