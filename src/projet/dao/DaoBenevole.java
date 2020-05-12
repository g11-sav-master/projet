package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;


public class DaoBenevole {

	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Benevole
			sql = "INSERT INTO benevole ( nom, prenom, e_mail, num_tel, date_naissance, login, mot_passe, possede_permis) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, benevole.getNom() );
			stmt.setString(	2, benevole.getPrenom() );
			stmt.setString(	3, benevole.getE_Mail() );
			stmt.setString(	4, benevole.getNumTel() );
			stmt.setObject(	5, benevole.getDateNaissance() );
			stmt.setString(	6, benevole.getLogin());
			stmt.setString(	7, "Mot de passe à changer rapidement" );
			// Il faudrait envoyer un mail dès la création afin d'obliger à se connecter pour changer le mot de passe
			stmt.setObject(	8, benevole.getPossedePermis());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			benevole.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return benevole.getId();
	}

	
	public void modifier(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le Benevole
			sql = "UPDATE benevole SET nom = ?, prenom = ?, e_mail = ?, num_tel = ?, date_naissance = ?, login = ?, possede_permis = ? WHERE id_utilisateur  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getNom() );
			stmt.setObject( 2, benevole.getPrenom() );
			stmt.setObject( 3, benevole.getE_Mail() );
			stmt.setObject( 4, benevole.getNumTel() );
			stmt.setObject( 5, benevole.getDateNaissance() );
			stmt.setObject( 6, benevole.getLogin() );
			stmt.setObject( 7, benevole.getPossedePermis() );
			stmt.setObject( 8, benevole.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


	}

	
	public void supprimer(int idBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le Benevole
			sql = "DELETE FROM benevole WHERE id_utilisateur = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idBenevole );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Benevole retrouver(int idBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE id_utilisateur = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idBenevole);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireBenevole(rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Benevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevole = new ArrayList<>();
			while (rs.next()) {
				benevole.add( construireBenevole(rs) );
			}
			return benevole;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Benevole construireBenevole( ResultSet rs ) throws SQLException {

		Benevole benevole = new Benevole();
		benevole.setId(rs.getObject( "idBenevole", Integer.class ));
		benevole.setNom(rs.getObject( "nom", String.class ));
		benevole.setPrenom(rs.getObject( "prenom", String.class ));
		benevole.setE_Mail(rs.getObject( "e_mail", String.class ));
		benevole.setNumTel(rs.getObject( "num_tel", String.class ));
		benevole.setDateNaissance(rs.getObject("date_naissance", LocalDate.class));
		benevole.setLogin(rs.getObject( "login", String.class ));
		benevole.setPossedePermis(rs.getObject( "possede_permis", Boolean.class ));

		return benevole;
	}
}
