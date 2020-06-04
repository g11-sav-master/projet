SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM utilisateur;
DELETE FROM role_benevole;
DELETE FROM raid;
DELETE FROM poste;
DELETE FROM benevole;
DELETE FROM categorie_raid;
DELETE FROM action_benevole;
DELETE FROM participant;
DELETE FROM participe_organisation;
DELETE FROM participant_duo;
DELETE FROM forme_duo;
DELETE FROM validation_medicale;


--Utilisateurs
INSERT INTO utilisateur (nom,prenom,e_mail,num_tel,date_naissance,login,mot_passe) 
		VALUES ('Robert', 'Isidore', 'robertisidore25@gmail.com', '0615252521', '15/02/82', 'isidu25', '2525isirob'),
		('Taner', 'Angie', 'AngTan65@gmail.com', '0615658525', '15/02/85', 'angietan65', 'angtanvapo45'),
		('Taner', 'Victor', 'VictTan65@gmail.com', '0715758725', '15/04/86', 'victan65', 'victanvapo45'),
		('Sapnz', 'Daurice', 'DauriceSz@gmail.com', '0799452410', '29/07/01', 'daudaudu01', 'duciva54'),
		('Jasp', 'Romain', 'RomJasp@gmail.com', '0764512101', '15/09/97', 'romtaxi81', 'vilayleckdu41'),
		('Grind', 'Pierrot', 'PiepieGri24@gmail.com', '0784546451', '21/06/02', 'PierrotGrigri21', 'rackvapta54'),
		('Hernandez', 'Sophia', 'SophiaHernandez54@gmail.com', '0777546941', '11/11/99', 'Sosodu54', 'jauspru54'),
		('Roth','Ishmael','neque@Curabitur.edu','0635222510','09/08/80','tincidunt,','sit'),
		('Frederick','Merrill','turpis@Classaptenttaciti.ca','0159654055','24/05/74','metus','mi'),
		('Montgomery','Thaddeus','ipsum@Vestibulumanteipsum.net','0898835858','22/11/80','magnis','ipsum'),
		('Rosa','Jasper','malesuada@Nullamscelerisque.com','0161315345','24/11/88','aliquet','purus'),
		('Manning','Bruno','neque@interdumliberodui.edu','0261467720','20/02/93','iaculis,','arcu'),
		('Holmes','Ignacia','cursus.luctus.ipsum@Donectemporest.edu','0162087802','15/09/03','erat','odio'),
		('Bates','Merritt','viverra.Maecenas@iaculis.net','0133651953','08/09/89','Mauriso','risus.'),
		('Larsen','Melanie','Quisque.tincidunt.pede@placerategetvenenatis.co.uk','0557892784','23/04/04','nisl.','Aliquam'),
		('Heath','Ariel','diam.Duis.mi@eunequepellentesque.co.uk','0805898063','05/11/89','vel,','elit.'),
		('Freeman','Amity','feugiat.metus@magnaDuis.org','0629631611','04/12/89','purus.','mauris'),
		('Sykes','Serena','non@necdiam.co.uk','0123605988','18/12/00','lobortis','felis'),
		('Hammond','Gwendolyn','mi@infaucibusorci.edu','0913067373','01/05/85','Praesent','ornare'),
		('Lancaster','Francesca','dapibus.ligula.Aliquam@loremacrisus.net','0264923508','11/06/90','enim,','eu'),
		('England','Lacey','magna.Nam@purusac.net','0835116973','17/02/03','malesuada','dictum'),
		('Banks','Cameron','Suspendisse.commodo.tincidunt@auctor.ca','0489387390','25/06/81','scelerisque','ante'),
		('White','Raphael','turpis.Nulla.aliquet@quis.org','0498271552','14/04/88','auctor,','massa'),
		('Travis','Maxine','Morbi.metus.Vivamus@ultriciesligula.net','0466682711','21/07/87','vitae','augue'),
		('Lynch','Jeanette','mollis.lectus.pede@pretium.net','0956711650','19/02/01','quis,','arcu.'),
		('Hamilton','Gregory','felis.eget@ami.org','0976860245','21/08/80','sociisa','eu,'),
		('Navarro','Shelley','feugiat.tellus.lorem@interdumligula.org','0287894183','24/02/02','ametesf','enim,'),
		('Merrill','Sheila','Cum@risusDonec.com','0279212419','04/06/95','mauris.','ipsum'),
		('Mcmahon','Christine','Phasellus.ornare@id.edu','0692431077','06/10/87','enima','quam,'),
		('Patterson','Audrey','cursus.et@Maecenasmi.net','0801206593','18/10/93','Vivamus','gravida'),
		('Alford','Ryder','dui@volutpat.ca','0283741243','14/09/03','inds,','risus.'),
		('Kirk','Glenna','mi@cursusaenim.co.uk','0245398428','30/05/81','et,','non'),
		('Forbes','Emerald','fringilla.euismod@purussapien.co.uk','0690464773','25/03/89','purus,','non,'),
		('Kane','Basia','lacinia@sedturpisnec.com','0250433161','20/11/96','turpis','magna.'),
		('Soto','Tiger','Vivamus.non.lorem@posuereenim.ca','0295102074','26/02/02','dapibus','Proin'),
		('Solis','Edward','egestas@conubia.co.uk','0700555284','11/03/86','malesuadaz','suscipit'),
		('Leonard','Chava','ultricies.dignissim@dui.co.uk','0295968459','11/04/85','penatibus','lectus'),
		('Webb','Dieter','elit.elit@eu.com','0435279523','04/06/94','Vivamuszdq','mauris,'),
		('Fox','Vielka','vulputate.risus.a@odio.ca','0588875864','03/12/87','Nunce','nulla.'),
		('Simpson','Elton','sit.amet.luctus@Nunccommodo.net','0318738791','24/09/80','consequat','tincidunt'),
		('Mckay','Nerea','ridiculus.mus@Vivamus.com','0602651948','22/02/02','augue','sem'),
		('Paul','Nelle','dolor.sit@turpisegestasFusce.edu','0268657285','20/07/01','Nulla','odio'),
		('Hood','Xavier','eleifend@Morbinequetellus.com','0628771886','12/05/01','mus.','arcu.'),
		('Love','Mason','egestas.urna.justo@fringillaDonecfeugiat.org','0237859835','20/04/04','maurisa','consequat,'),
		('George','Travis','ultrices.posuere.cubilia@dui.net','0252963036','13/01/88','ac2','sollicitudin'),
		('Fowler','Dexter','molestie@loremauctorquis.com','0536824317','08/12/89','ametdsf','leo.'),
		('Howard','Harriet','ut.eros.non@Aliquameratvolutpat.net','0466743274','10/12/87','vehicula','nec'),
		('Meadows','Ryan','lectus@semconsequatnec.org','0408789683','07/05/83','fermentum','amet,'),
		('Coffey','Samson','In@nisiCum.edu','0161410136','24/12/03','Duis','turpis'),
		('Cox','Chantale','magna@sitametmassa.com','0460345850','06/05/82','amet','risus.'),
		('Rivas','Yardley','tortor.nibh@Uttincidunt.co.uk','0893471364','17/11/80','Nullam','sit'),
		('Cervantes','Tamekah','Integer@orciDonec.co.uk','0385554743','20/10/01','ac7','enim.'),
		('Valencia','Ori','justo.Proin@mieleifend.edu','0826792291','02/12/80','Quisque','a'),
		('Simpson','Sonya','eu@in.co.uk','0154186253','27/06/82','a,','mauris'),
		('Charles','Armand','sem.consequat@Morbi.edu','0299604921','07/11/80','in,','nec'),
		('Berg','Jason','iaculis.enim.sit@sed.com','0414864769','28/10/96','nisl','Cras'),
		('Dominguez','Alexa','eleifend.nec.malesuada@necmalesuada.edu','0489309113','29/08/89','nibhzqszqd','Fusce'),
		('Slater','Micah','molestie@pedeNunc.com','0883662769','15/11/03','mollis','mauris'),
		('Gross','Jocelyn','cubilia@metusvitaevelit.net','0445057066','09/06/00','non','a,'),
		('Thomas','Holmes','diam.dictum@Sedpharetra.net','0993756894','18/08/82','enim','scelerisque'),
		('Barnett','Tasha','accumsan.sed.facilisis@Nullaaliquet.edu','0726808778','28/03/00','felis.','nec'),
		('Tucker','Odysseus','molestie.pharetra@cubiliaCuraePhasellus.ca','0362182193','26/03/86','nec,','lectus'),
		('Prince','Yoshio','tellus@urnaNuncquis.org','0999664638','09/03/83','Quisquesqd','arcu'),
		('Watts','Cally','placerat@Nam.edu','0593638676','24/04/81','eget','nibhzqd'),
		('Glass','Barclay','vitae.orci.Phasellus@vitae.com','0444244057','31/07/85','nascetur','dui.'),
		('Travis','Genevieve','commodo.auctor.velit@Cumsociis.org','0345111162','28/05/01','consectetuer','sed'),
		('Hubbard','Unity','lacus.vestibulum@justofaucibuslectus.net','0893051609','24/12/00','nasceturqsfd','porttitor'),
		('Velez','Minerva','sit.amet.diam@quistristiqueac.org','0290718944','08/04/02','ut,','blandit'),
		('Buck','Audrey','interdum.Curabitur.dictum@nasceturridiculus.org','0839868873','28/02/91','sociis','Proin'),
		('Austin','Tatyana','pede.Nunc.sed@adipiscingelit.ca','0354064165','23/05/94','dui,','aliquet,'),
		('Horn','Yvette','Morbi.metus@dapibusrutrum.edu','0951499673','04/09/98','tellus','aliquet'),
		('Juarez','Joshua','Curabitur.vel@Aliquam.edu','0728209999','17/04/01','Aliquam','Fusce'),
		('Gillespie','Abigail','dui.nec@ullamcorperDuis.co.uk','0976176519','17/07/02','id','turpis.'),
		('Todd','Donovan','nunc@liberolacus.edu','0156921588','23/08/80','mauris','tristique'),
		('Battle','Victoria','suscipit.nonummy.Fusce@porttitoreros.com','0363305509','26/04/82','pharetra,','erat'),
		('Cotton','Thor','Cum.sociis.natoque@Classaptent.edu','0619348250','30/01/71','nibh','sit'),
		('Monroe','Byron','mollis@ProindolorNulla.com','0288921816','23/02/92','vestibulum','pede.'),
		('Baker','Nevada','Nunc.lectus@tinciduntorciquis.ca','0928663674','12/04/94','Etiam','nec,'),
		('Lara','Martha','Aliquam@fermentummetus.com','0640773117','01/08/87','nisla','egestas.'),
		('Bullock','Coby','Aenean@orci.co.uk','0433715921','20/04/86','ipsum','enim.'),
		 ('Lawrence','Shea','molestie.dapibus.ligula@Nunclectus.org','0321958654','26/03/85','Mauris','felis'),
		 ('Rasmussen','Adena','nec@dis.co.uk','0528158458','16/05/79','tristique','libero.'),
		 ('Pena','Igor','odio.auctor.vitae@fringillaDonec.edu','0834122349','18/12/72','Nunc','dictum'),
		 ('Obrien','Wade','turpis.Aliquam.adipiscing@consequat.edu','0319580592','13/08/72','Praesente','enim.'),
		 ('Lindsay','Raphael','mollis.non@vulputate.co.uk','0506660721','10/08/79','libero','turpis'),
		 ('Hester','Donna','vel.pede@euenimEtiam.com','0605832694','24/10/02','ultrices.','magnis'),
		 ('Jefferson','Martin','vitae@magnaUttincidunt.ca','0598698788','22/05/72','placerat,','felis'),
		 ('Bullock','Jason','elit.erat.vitae@adipiscing.co.uk','0874046446','29/08/75','amet,','nisi'),
		 ('Bass','Basia','lacus.Aliquam@vitae.net','0441600271','16/01/01','odio.','eu,'),
		 ('Petty','Sylvester','dignissim.lacus@arcu.co.uk','0926235060','27/09/88','auctoro','purus,'),
		 ('Mcpherson','Irene','Ut.tincidunt@tortoratrisus.org','0953454107','25/08/01','dignissim.','ipsum'),
		 ('Goff','Elliott','felis@Nunccommodoauctor.ca','0650919543','21/05/77','vitaea','porttitor'),
		 ('Hogan','Abigail','diam.nunc@tinciduntorci.org','0668055948','11/08/88','parturient','a'),
		('Bray','Kameko','auctor.non@dictumultricies.org','0219187751','14/02/88','etefcsq,','hendrerit'),
		 ('Rollins','Ivor','a.magna.Lorem@etmagnaPraesent.co.uk','0161395013','08/09/77','lacus','nec,'),
		 ('Horne','Cally','in@sagittissemper.org','0130584123','03/10/75','Aenean','nec'),
		 ('Crosby','Karly','enim.gravida.sit@tellusAeneanegestas.org','0822706301','19/07/75','porttitor','litora'),
		 ('Fuentes','Ori','feugiat.Lorem.ipsum@sagittisNullamvitae.ca','0162144386','10/09/01','sit','vehicula'),
		 ('Richardson','Thomas','urna.Vivamus.molestie@Phasellusornare.com','0371332750','16/01/79','acz','quis'),
		 ('Moss','Jerome','Nunc.mauris.elit@liberolacus.org','0662634579','03/10/01','diam.','consequat');

--Rôles-Bénévole
INSERT INTO role_benevole (nom_role)
	VALUES ('admin'),('role1'),('role2'),('role3'),('role4');
--Raids

INSERT INTO raid 
	VALUES (1,'Bol d''air 2020', '20/08/2020', '25', '7'),
			(2,'Mini Bol d''air 2020', '22/08/2020', '20', '7');

--Postes
INSERT INTO poste VALUES ('1', '1', '3');
INSERT INTO poste VALUES ('2', '1', '2');
INSERT INTO poste VALUES ('3', '1', '2');
INSERT INTO poste VALUES ('4', '1', '3');
INSERT INTO poste VALUES ('5', '1', '2');
INSERT INTO poste VALUES ('6', '1', '3');
INSERT INTO poste VALUES ('7', '2', '3');
INSERT INTO poste VALUES ('8', '2', '2');
INSERT INTO poste VALUES ('9', '2', '2');
INSERT INTO poste VALUES ('10', '2', '3');
INSERT INTO poste VALUES ('11', '2', '2');
INSERT INTO poste VALUES ('12', '2', '3');

--Bénévoles

INSERT INTO benevole VALUES ('1','1','true');
INSERT INTO benevole VALUES ('2','2','false');
INSERT INTO benevole VALUES ('3','3','true');
INSERT INTO benevole VALUES ('4','4','true');
INSERT INTO benevole VALUES ('5','5','false');
INSERT INTO benevole VALUES ('6','1','true');
INSERT INTO benevole VALUES ('7','5','false');
INSERT INTO benevole VALUES ('8','2','true');
INSERT INTO benevole VALUES ('9','4','true');
INSERT INTO benevole VALUES ('10','3','true');
INSERT INTO benevole VALUES ('11','3','false');
INSERT INTO benevole VALUES ('12','5','true');
INSERT INTO benevole VALUES ('13','5','false');
INSERT INTO benevole VALUES ('14','3','true');
INSERT INTO benevole VALUES ('15','3','true');
INSERT INTO benevole VALUES ('16','4','true');
INSERT INTO benevole VALUES ('17','2','false');
INSERT INTO benevole VALUES ('18','5','true');
INSERT INTO benevole VALUES ('19','1','true');
INSERT INTO benevole VALUES ('20','3','true');
INSERT INTO benevole VALUES ('21','1','true');
INSERT INTO benevole VALUES ('22','5','false');
INSERT INTO benevole VALUES ('23','2','true');
INSERT INTO benevole VALUES ('24','4','true');
INSERT INTO benevole VALUES ('25','2','false');
INSERT INTO benevole VALUES ('26','2','true');
INSERT INTO benevole VALUES ('27','4','true');
INSERT INTO benevole VALUES ('28','2','false');
INSERT INTO benevole VALUES ('29','3','true');
INSERT INTO benevole VALUES ('30','2','false');

--Actions bénévole

INSERT INTO action_benevole VALUES ('1','1','1','true','descr1','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('2','2','2','false','descr2','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('3','3','3','true','descr3','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('4','4','4','true','descr4','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('5','5','5','true','descr5','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('6','6','6','false','descr6','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('7','7','7','true','descr7','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('8','8','8','true','descr8','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('9','9','9','false','descr9','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('10','10','10','true','descr10','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('11','11','11','true','descr11','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('12','12','12','false','descr12','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('13','1','13','true','descr1','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('14','2','14','false','descr2','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('15','3','15','true','descr3','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('16','4','16','true','descr4','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('17','5','17','true','descr5','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('18','6','18','false','descr6','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('19','7','19','true','descr7','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('20','8','20','true','descr8','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('21','9','21','false','descr9','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('22','10','22','true','descr10','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('23','11','23','true','descr11','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('24','12','24','false','descr12','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('25','1','25','true','descr1','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('26','4','26','true','descr4','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('27','6','27','false','descr6','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('28','7','28','true','descr7','true','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('29','10','29','true','descr10','false','13:30:00','15:30:00');
INSERT INTO action_benevole VALUES ('30','12','30','false','descr12','true','13:30:00','15:30:00');


--Catégories raid

INSERT INTO categorie_raid VALUES ('1','Homme');
INSERT INTO categorie_raid VALUES ('2','Femme');
INSERT INTO categorie_raid VALUES ('3','Mixte');
INSERT INTO categorie_raid VALUES ('4','VAE');


--Participants
INSERT INTO participant VALUES (31,'club1');
INSERT INTO participant VALUES (32,'club1');
INSERT INTO participant VALUES (33,'club2');
INSERT INTO participant VALUES (34,'club2');
INSERT INTO participant VALUES (35,'club3');
INSERT INTO participant VALUES (36,'club3');
INSERT INTO participant VALUES (37,'club4');
INSERT INTO participant VALUES (38,'club4');
INSERT INTO participant VALUES (39,'club1');
INSERT INTO participant VALUES (40,'club1');
INSERT INTO participant VALUES (41,'club1');
INSERT INTO participant VALUES (42,'club1');
INSERT INTO participant VALUES (43,'club2');
INSERT INTO participant VALUES (44,'club2');
INSERT INTO participant VALUES (45,'club2');
INSERT INTO participant VALUES (46,'club2');
INSERT INTO participant VALUES (47,'club3');
INSERT INTO participant VALUES (48,'club3');
INSERT INTO participant VALUES (49,'club4');
INSERT INTO participant VALUES (50,'club4');
INSERT INTO participant VALUES (51,'club4');
INSERT INTO participant VALUES (52,'club4');
INSERT INTO participant VALUES (53,'club2');
INSERT INTO participant VALUES (54,'club2');
INSERT INTO participant VALUES (55,'club1');
INSERT INTO participant VALUES (56,'club1');
INSERT INTO participant VALUES (57,'club2');
INSERT INTO participant VALUES (58,'club2');
INSERT INTO participant VALUES (59,'club3');
INSERT INTO participant VALUES (60,'club3');
INSERT INTO participant VALUES (61,'club3');
INSERT INTO participant VALUES (62,'club3');
INSERT INTO participant VALUES (63,'club4');
INSERT INTO participant VALUES (64,'club4');
INSERT INTO participant VALUES (65,'club2');
INSERT INTO participant VALUES (66,'club2');
INSERT INTO participant VALUES (67,'club1');
INSERT INTO participant VALUES (68,'club1');
INSERT INTO participant VALUES (69,'club4');
INSERT INTO participant VALUES (70,'club4');
INSERT INTO participant VALUES (71,'club2');
INSERT INTO participant VALUES (72,'club2');
INSERT INTO participant VALUES (73,'club3');
INSERT INTO participant VALUES (74,'club3');
INSERT INTO participant VALUES (75,'club1');
INSERT INTO participant VALUES (76,'club1');
INSERT INTO participant VALUES (77,'club2');
INSERT INTO participant VALUES (78,'club2');
INSERT INTO participant VALUES (79,'club3');
INSERT INTO participant VALUES (80,'club3');
INSERT INTO participant VALUES (81,'club4');
INSERT INTO participant VALUES (82,'club4');
INSERT INTO participant VALUES (83,'club2');
INSERT INTO participant VALUES (84,'club2');
INSERT INTO participant VALUES (85,'club3');
INSERT INTO participant VALUES (86,'club3');
INSERT INTO participant VALUES (87,'club1');
INSERT INTO participant VALUES (88,'club1');
INSERT INTO participant VALUES (89,'club4');
INSERT INTO participant VALUES (90,'club4');
INSERT INTO participant VALUES (91,'club3');
INSERT INTO participant VALUES (92,'club3');
INSERT INTO participant VALUES (93,'club1');
INSERT INTO participant VALUES (94,'club1');
INSERT INTO participant VALUES (95,'club1');
INSERT INTO participant VALUES (96,'club1');
INSERT INTO participant VALUES (97,'club2');
INSERT INTO participant VALUES (98,'club2');
INSERT INTO participant VALUES (99,'club4');
INSERT INTO participant VALUES (100,'club4');

--Participant duo

INSERT INTO participant_duo VALUES ('1', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('2', '2', '2', '3', 'true');
INSERT INTO participant_duo VALUES ('3', '3', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('4', '4', '2', '2', 'false');
INSERT INTO participant_duo VALUES ('5', '1', '1', '4', 'false');
INSERT INTO participant_duo VALUES ('6', '2', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('7', '3', '1', '2', 'false');
INSERT INTO participant_duo VALUES ('8', '4', '2', '3', 'false');
INSERT INTO participant_duo VALUES ('9', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('10', '2', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('11', '3', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('12', '4', '2', '4', 'true');
INSERT INTO participant_duo VALUES ('13', '1', '1', '4', 'true');
INSERT INTO participant_duo VALUES ('14', '2', '2', '4', 'false');
INSERT INTO participant_duo VALUES ('15', '3', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('16', '4', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('17', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('18', '2', '2', '2', 'false');
INSERT INTO participant_duo VALUES ('19', '3', '1', '4', 'true');
INSERT INTO participant_duo VALUES ('20', '4', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('21', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('22', '2', '2', '3', 'true');
INSERT INTO participant_duo VALUES ('23', '3', '1','2', 'true');
INSERT INTO participant_duo VALUES ('24', '4', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('25', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('26', '2', '2','2', 'true');
INSERT INTO participant_duo VALUES ('27', '3', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('28', '4', '2', '2', 'false');
INSERT INTO participant_duo VALUES ('29', '1', '1', '4', 'true');
INSERT INTO participant_duo VALUES ('30', '2', '2', '2', 'true');
INSERT INTO participant_duo VALUES ('31', '3', '1', '2', 'false');
INSERT INTO participant_duo VALUES ('32', '4', '2','2', 'true');
INSERT INTO participant_duo VALUES ('33', '1', '1', '2', 'true');
INSERT INTO participant_duo VALUES ('34', '2', '2','2', 'true');
INSERT INTO participant_duo VALUES ('35', '3', '1','2', 'true');

--Forme duo

INSERT INTO forme_duo VALUES ('1','31','false');
INSERT INTO forme_duo VALUES ('1','32','true');
INSERT INTO forme_duo VALUES ('2','33','false');
INSERT INTO forme_duo VALUES ('2','34','true');
INSERT INTO forme_duo VALUES ('3','35','false');
INSERT INTO forme_duo VALUES ('3','36','true');
INSERT INTO forme_duo VALUES ('4','37','true');
INSERT INTO forme_duo VALUES ('4','38','false');
INSERT INTO forme_duo VALUES ('5','39','true');
INSERT INTO forme_duo VALUES ('5','40','false');
INSERT INTO forme_duo VALUES ('6','41','false');
INSERT INTO forme_duo VALUES ('6','42','true');
INSERT INTO forme_duo VALUES ('7','43','true');
INSERT INTO forme_duo VALUES ('7','44','false');
INSERT INTO forme_duo VALUES ('8','45','false');
INSERT INTO forme_duo VALUES ('8','46','true');
INSERT INTO forme_duo VALUES ('9','47','false');
INSERT INTO forme_duo VALUES ('9','48','true');
INSERT INTO forme_duo VALUES ('10','49','false');
INSERT INTO forme_duo VALUES ('10','50','true');
INSERT INTO forme_duo VALUES ('11','51','true');
INSERT INTO forme_duo VALUES ('11','52','false');
INSERT INTO forme_duo VALUES ('12','53','false');
INSERT INTO forme_duo VALUES ('12','54','true');
INSERT INTO forme_duo VALUES ('13','55','false');
INSERT INTO forme_duo VALUES ('13','56','true');
INSERT INTO forme_duo VALUES ('14','57','true');
INSERT INTO forme_duo VALUES ('14','58','false');
INSERT INTO forme_duo VALUES ('15','59','false');
INSERT INTO forme_duo VALUES ('15','60','true');
INSERT INTO forme_duo VALUES ('16','61','false');
INSERT INTO forme_duo VALUES ('16','62','true');
INSERT INTO forme_duo VALUES ('17','63','false');
INSERT INTO forme_duo VALUES ('17','64','true');
INSERT INTO forme_duo VALUES ('18','65','false');
INSERT INTO forme_duo VALUES ('18','66','true');
INSERT INTO forme_duo VALUES ('19','67','true');
INSERT INTO forme_duo VALUES ('19','68','false');
INSERT INTO forme_duo VALUES ('20','69','false');
INSERT INTO forme_duo VALUES ('20','70','true');
INSERT INTO forme_duo VALUES ('21','71','true');
INSERT INTO forme_duo VALUES ('21','72','false');
INSERT INTO forme_duo VALUES ('22','73','false');
INSERT INTO forme_duo VALUES ('22','74','true');
INSERT INTO forme_duo VALUES ('23','75','false');
INSERT INTO forme_duo VALUES ('23','76','true');
INSERT INTO forme_duo VALUES ('24','77','true');
INSERT INTO forme_duo VALUES ('24','78','false');
INSERT INTO forme_duo VALUES ('25','79','true');
INSERT INTO forme_duo VALUES ('25','80','false');
INSERT INTO forme_duo VALUES ('26','81','false');
INSERT INTO forme_duo VALUES ('26','82','true');
INSERT INTO forme_duo VALUES ('27','83','false');
INSERT INTO forme_duo VALUES ('27','84','true');
INSERT INTO forme_duo VALUES ('28','85','true');
INSERT INTO forme_duo VALUES ('28','86','false');
INSERT INTO forme_duo VALUES ('29','87','false');
INSERT INTO forme_duo VALUES ('29','88','true');
INSERT INTO forme_duo VALUES ('30','89','false');
INSERT INTO forme_duo VALUES ('30','90','true');
INSERT INTO forme_duo VALUES ('31','91','false');
INSERT INTO forme_duo VALUES ('31','92','true');
INSERT INTO forme_duo VALUES ('32','93','true');
INSERT INTO forme_duo VALUES ('32','94','false');
INSERT INTO forme_duo VALUES ('33','95','true');
INSERT INTO forme_duo VALUES ('33','96','false');
INSERT INTO forme_duo VALUES ('34','97','true');
INSERT INTO forme_duo VALUES ('34','98','false');
INSERT INTO forme_duo VALUES ('35','99','false');
INSERT INTO forme_duo VALUES ('35','100','true');
--Participe organisation
INSERT INTO participe_organisation VALUES ('1','1','2');
INSERT INTO participe_organisation VALUES ('1','2','1');
INSERT INTO participe_organisation VALUES ('1','3','3');
INSERT INTO participe_organisation VALUES ('1','4','2');
INSERT INTO participe_organisation VALUES ('1','5','4');
INSERT INTO participe_organisation VALUES ('1','6','1');
INSERT INTO participe_organisation VALUES ('1','7','1');
INSERT INTO participe_organisation VALUES ('1','8','3');
INSERT INTO participe_organisation VALUES ('1','9','1');
INSERT INTO participe_organisation VALUES ('1','10','2');
INSERT INTO participe_organisation VALUES ('1','11','1');
INSERT INTO participe_organisation VALUES ('1','12','1');
INSERT INTO participe_organisation VALUES ('1','13','1');
INSERT INTO participe_organisation VALUES ('1','14','1');
INSERT INTO participe_organisation VALUES ('1','15','1');
INSERT INTO participe_organisation VALUES ('2','16','3');
INSERT INTO participe_organisation VALUES ('2','17','2');
INSERT INTO participe_organisation VALUES ('2','18','2');
INSERT INTO participe_organisation VALUES ('2','19','2');
INSERT INTO participe_organisation VALUES ('2','20','1');
INSERT INTO participe_organisation VALUES ('2','21','2');
INSERT INTO participe_organisation VALUES ('2','22','1');
INSERT INTO participe_organisation VALUES ('2','23','4');
INSERT INTO participe_organisation VALUES ('2','24','2');
INSERT INTO participe_organisation VALUES ('2','25','2');
INSERT INTO participe_organisation VALUES ('2','26','1');
INSERT INTO participe_organisation VALUES ('2','27','1');
INSERT INTO participe_organisation VALUES ('2','28','1');
INSERT INTO participe_organisation VALUES ('2','29','1');
INSERT INTO participe_organisation VALUES ('2','30','2');


--validation médicale

INSERT INTO validation_medicale VALUES ('1','31', 'true', '30/12/25');
INSERT INTO validation_medicale VALUES ('2','32', 'false', '15/07/09');
INSERT INTO validation_medicale VALUES ('3','33', 'true', '14/06/22');
INSERT INTO validation_medicale VALUES ('4','34', 'true', '05/03/23');
INSERT INTO validation_medicale VALUES ('5','35', 'true', '19/11/22');
INSERT INTO validation_medicale VALUES ('6','36', 'true', '18/04/24');
INSERT INTO validation_medicale VALUES ('7','37', 'false', '20/05/15');
INSERT INTO validation_medicale VALUES ('8','38', 'true', '10/05/24');
INSERT INTO validation_medicale VALUES ('9','39', 'true', '02/04/25');
INSERT INTO validation_medicale VALUES ('10','40', 'true', '13/10/23');
INSERT INTO validation_medicale VALUES ('11','41', 'true', '12/09/24');
INSERT INTO validation_medicale VALUES ('12','42', 'false', '29/08/19');
INSERT INTO validation_medicale VALUES ('13','43', 'true', '03/07/26');
INSERT INTO validation_medicale VALUES ('14','44', 'true', '06/04/24');
INSERT INTO validation_medicale VALUES ('15','45', 'true', '20/10/22');
INSERT INTO validation_medicale VALUES ('16','46', 'true', '26/06/23');
INSERT INTO validation_medicale VALUES ('17','47', 'true', '12/06/21');
INSERT INTO validation_medicale VALUES ('18','48', 'true', '17/05/22');
INSERT INTO validation_medicale VALUES ('19','49', 'true', '18/01/23');
INSERT INTO validation_medicale VALUES ('20','50', 'true', '20/07/22');
INSERT INTO validation_medicale VALUES ('21','51', 'true', '22/04/22');
INSERT INTO validation_medicale VALUES ('22','52', 'true', '11/12/24');
INSERT INTO validation_medicale VALUES ('23','53', 'true', '19/08/25');
INSERT INTO validation_medicale VALUES ('24','54', 'true', '15/02/24');
INSERT INTO validation_medicale VALUES ('25','55', 'true', '18/06/21');
INSERT INTO validation_medicale VALUES ('26','56', 'true', '16/03/22');
INSERT INTO validation_medicale VALUES ('27','57', 'true', '26/02/26');
INSERT INTO validation_medicale VALUES ('28','58', 'true', '27/11/22');
INSERT INTO validation_medicale VALUES ('29','59', 'true', '16/12/24');
INSERT INTO validation_medicale VALUES ('30','60', 'true', '28/08/23');
INSERT INTO validation_medicale VALUES ('31','61', 'true', '29/09/25');
INSERT INTO validation_medicale VALUES ('32','62', 'true', '23/07/24');
INSERT INTO validation_medicale VALUES ('33','63', 'true', '29/07/25');
INSERT INTO validation_medicale VALUES ('34','64', 'false', '18/06/19');
INSERT INTO validation_medicale VALUES ('35','65', 'true', '25/04/28');
INSERT INTO validation_medicale VALUES ('36','66', 'true', '10/03/22');
INSERT INTO validation_medicale VALUES ('37','67', 'true', '26/03/25');
INSERT INTO validation_medicale VALUES ('38','68', 'true', '24/02/24');
INSERT INTO validation_medicale VALUES ('39','69', 'true', '07/02/24');
INSERT INTO validation_medicale VALUES ('40','70', 'true', '29/01/23');
INSERT INTO validation_medicale VALUES ('41','71', 'true', '31/01/24');
INSERT INTO validation_medicale VALUES ('42','72', 'true', '01/02/24');
INSERT INTO validation_medicale VALUES ('43','73', 'true', '08/01/24');
INSERT INTO validation_medicale VALUES ('44','74', 'true', '21/03/22');
INSERT INTO validation_medicale VALUES ('45','75', 'true', '13/03/26');
INSERT INTO validation_medicale VALUES ('46','76', 'true', '15/01/21');
INSERT INTO validation_medicale VALUES ('47','77', 'true', '14/03/22');
INSERT INTO validation_medicale VALUES ('48','78', 'true', '11/11/26');
INSERT INTO validation_medicale VALUES ('49','79', 'true', '24/04/23');
INSERT INTO validation_medicale VALUES ('50','80', 'true', '24/02/24');
INSERT INTO validation_medicale VALUES ('51','81', 'true', '26/05/24');
INSERT INTO validation_medicale VALUES ('52','82', 'true', '14/05/23');
INSERT INTO validation_medicale VALUES ('53','83', 'true', '22/03/23');
INSERT INTO validation_medicale VALUES ('54','84', 'true', '26/04/22');
INSERT INTO validation_medicale VALUES ('55','85', 'true', '22/03/21');
INSERT INTO validation_medicale VALUES ('56','86', 'true', '12/03/25');
INSERT INTO validation_medicale VALUES ('57','87', 'true', '31/03/21');
INSERT INTO validation_medicale VALUES ('58','88', 'true', '17/09/22');
INSERT INTO validation_medicale VALUES ('59','89', 'true', '10/08/26');
INSERT INTO validation_medicale VALUES ('60','90', 'false', '06/07/16');
INSERT INTO validation_medicale VALUES ('61','91', 'true', '06/10/26');
INSERT INTO validation_medicale VALUES ('62','92', 'true', '01/09/23');
INSERT INTO validation_medicale VALUES ('63','93', 'true', '06/04/23');
INSERT INTO validation_medicale VALUES ('64','94', 'true', '16/12/23');
INSERT INTO validation_medicale VALUES ('65','95', 'true', '18/08/23');
INSERT INTO validation_medicale VALUES ('66','96', 'true', '01/09/25');
INSERT INTO validation_medicale VALUES ('67','97', 'false', '24/07/18');
INSERT INTO validation_medicale VALUES ('68','98', 'true', '16/08/23');
INSERT INTO validation_medicale VALUES ('69','99', 'true', '24/09/25');
INSERT INTO validation_medicale VALUES ('70','100', 'true', '12/07/24');
