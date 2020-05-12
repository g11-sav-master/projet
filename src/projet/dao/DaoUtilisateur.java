package projet.dao;

import java.sql.CallableStatement;
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
import projet.data.Utilisateur;


public class DaoUtilisateur {

	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Utilisateur utilisateur)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le utilisateur
			sql = "INSERT INTO utilisateur ( nom, prenom, e_mail, num_tel, date_naissance, login, mot_passe) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, utilisateur.getNom() );
			stmt.setString(	2, utilisateur.getPrenom() );
			stmt.setString(	3, utilisateur.getE_Mail() );
			stmt.setString(	4, utilisateur.getNumTel() );
			stmt.setObject(	5, utilisateur.getDateNaissance() );
			stmt.setString(	6, utilisateur.getLogin());
			stmt.setString(	7, "Mot de passe à changer rapidement" );
			// Il faudrait envoyer un mail dès la création afin d'obliger à se connecter pour changer le mot de passe
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			utilisateur.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return utilisateur.getId();
	}

	
	public void modifier(Utilisateur utilisateur)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le utilisateur
			sql = "UPDATE utilisateur SET nom = ?, prenom = ?, e_mail = ?, num_tel = ?, date_naissance = ?, login = ? WHERE id_utilisateur  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, utilisateur.getNom() );
			stmt.setObject( 2, utilisateur.getPrenom() );
			stmt.setObject( 3, utilisateur.getE_Mail() );
			stmt.setObject( 4, utilisateur.getNumTel() );
			stmt.setObject( 5, utilisateur.getDateNaissance() );
			stmt.setObject( 6, utilisateur.getLogin() );
			stmt.setObject( 7,  utilisateur.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

	}

	
	public void supprimer(int idUtilisateur)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le utilisateur
			sql = "DELETE FROM utilisateur WHERE id_utilisateur = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idUtilisateur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Utilisateur retrouver(int idUtilisateur)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idUtilisateur);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireUtilisateur(rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Utilisateur> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Utilisateur> utilisateur = new ArrayList<>();
			while (rs.next()) {
				utilisateur.add( construireUtilisateur(rs) );
			}
			return utilisateur;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Utilisateur construireUtilisateur( ResultSet rs ) throws SQLException {

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(rs.getObject( "id_utilisateur", Integer.class ));
		utilisateur.setNom(rs.getObject( "nom", String.class ));
		utilisateur.setPrenom(rs.getObject( "prenom", String.class ));
		utilisateur.setE_Mail(rs.getObject( "e_mail", String.class ));
		utilisateur.setNumTel(rs.getObject( "num_tel", String.class ));
		utilisateur.setDateNaissance(rs.getObject("date_naissance", LocalDate.class));
		utilisateur.setLogin(rs.getObject( "login", String.class ));

		return utilisateur;
	}
	
	public Utilisateur validerAuthentification( String pseudo, String motDePasse )  {
		Connection			cn		= null;
		CallableStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "{ CALL compte_valider_authentification( ?, ? ) } ";
			stmt = cn.prepareCall( sql );
			stmt.setObject( 1, pseudo );
			stmt.setObject( 2, motDePasse );
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireUtilisateur( rs );			
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
}