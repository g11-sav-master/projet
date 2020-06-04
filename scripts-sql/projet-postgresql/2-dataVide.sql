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
		VALUES ('Robert', 'Isidore', 'robertisidore25@gmail.com', '0615252521', '15/02/82', 'isidu25', '2525isirob');

--Rôles-Bénévole
INSERT INTO role_benevole (nom_role)
	VALUES ('Administrateur');
	
--Bénévoles

INSERT INTO benevole VALUES ('1','1','true');