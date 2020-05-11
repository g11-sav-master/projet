SET search_path TO projet;


-- Supprime toutes les fonctions du schéma

DO $ccode$
DECLARE 
	r RECORD;
BEGIN
	FOR r IN
		SELECT 'DROP FUNCTION ' || ns.nspname || '.' || proname 
	       || '(' || oidvectortypes(proargtypes) || ')' AS sql
		FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
		WHERE ns.nspname = 'projet'  
	LOOP
		EXECUTE r.sql;
	END LOOP;
END;
$ccode$;


-- Fonctions

CREATE FUNCTION compte_inserer(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_email			VARCHAR,
	p_idcompte 	OUT	INT,
	p_roles			VARCHAR[]
) 
AS $ccode$
DECLARE v_role VARCHAR;
BEGIN
	INSERT INTO o_compte ( pseudo, motdepasse, email )
	VALUES ( p_pseudo, p_motdepasse,p_email )
	RETURNING idcompte INTO p_idcompte;
	
	FOREACH v_role IN ARRAY p_roles LOOP
		INSERT INTO o_role ( idcompte, role )
		VALUES ( p_idcompte, v_role );
	END LOOP;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_modifier(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_email			VARCHAR,
	p_idcompte 		INT,
	p_roles			VARCHAR[]
) 
RETURNS VOID 
AS $ccode$
DECLARE v_role VARCHAR;
BEGIN
	UPDATE o_compte 
	SET pseudo = p_pseudo, 
		motdepasse = p_motdepasse, 
		email = p_email 
	WHERE idcompte =  p_idcompte;

	DELETE FROM o_role WHERE idcompte = p_idcompte;
	
	FOREACH v_role IN ARRAY p_roles LOOP
		INSERT INTO o_role ( idcompte, role )
		VALUES ( p_idcompte, v_role );
	END LOOP;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_supprimer( p_idCompte INT ) 
RETURNS VOID 
AS $ccode$
BEGIN
	DELETE FROM o_role WHERE idcompte = p_idcompte;
	DELETE FROM o_compte WHERE idcompte = p_idcompte;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION compte_retrouver( p_idCompte INT ) 
--RETURNS TABLE (
--    idcompte    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*, ARRAY_AGG( r.role ) AS roles
--	FROM compte c 
--	LEFT JOIN role r ON c.idcompte = r.idcompte
--	WHERE c.idcompte = p_idcompte
--	GROUP BY c.idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_retrouver( p_idCompte INT ) 
RETURNS SETOF o_v_compte_avec_roles 
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM o_v_compte_avec_roles
	WHERE idcompte = p_idcompte;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_lister_tout() 
RETURNS SETOF o_v_compte_avec_roles 
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM o_v_compte_avec_roles 
	ORDER BY pseudo;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION compte_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
--RETURNS TABLE (
--    idcompte    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*,  ARRAY_AGG( r.role ) AS roles
--	FROM compte c 
--	LEFT JOIN role r ON c.idcompte = r.idcompte
--	WHERE c.pseudo = P_pseudo
--	  AND c.motdepasse = p_motdepasse
--	GROUP BY c.idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;


--
-- p_login 			VARCHAR : login de l'utilisateur
-- p_mot_passe		VARCHAR : mot de passe de l'utilisateur
--
CREATE FUNCTION compte_valider_authentification( p_login VARCHAR, p_motdepasse VARCHAR ) 
RETURNS SETOF v_utilisateur_avec_roles
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM v_utilisateur_avec_roles
	WHERE login = p_login
		AND mot_passe = p_motdepasse;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION compte_verifier_unicite_pseudo(
	p_pseudo 		VARCHAR,
	p_idcompte 		INT,
	OUT p_unicite	BOOLEAN
) 
AS $ccode$
BEGIN
	SELECT COUNT(*) = 0 INTO p_unicite
	FROM o_compte
	WHERE pseudo = p_pseudo
	  AND idcompte <> P_idcompte;
END;
$ccode$ LANGUAGE plpgsql;


