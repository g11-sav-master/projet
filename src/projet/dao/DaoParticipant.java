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
import projet.data.Participant;

public class DaoParticipant {

	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Participant
			sql = "INSERT INTO participant ( nom, prenom, e_mail, num_tel, date_naissance, login, mot_passe, club) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, participant.getNom() );
			stmt.setString(	2, participant.getPrenom() );
			stmt.setString(	3, participant.getE_Mail() );
			stmt.setString(	4, participant.getNumTel() );
			stmt.setObject(	5, participant.getDateNaissance() );
			stmt.setString(	6, participant.getLogin());
			stmt.setString(	7, "Mot de passe à changer rapidement" );
			// Il faudrait envoyer un mail dès la création afin d'obliger à se connecter pour changer le mot de passe
			stmt.setString(8,  participant.getClub());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			participant.setId( rs.getObject( "id_utilisateur", Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return participant.getId();
	}

	
	public void modifier(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le Participant
			sql = "UPDATE participant SET nom = ?, prenom = ?, e_mail = ?, num_tel = ?, date_naissance = ?, login = ? WHERE id_utilisateur  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, participant.getNom() );
			stmt.setObject( 2, participant.getPrenom() );
			stmt.setObject( 3, participant.getE_Mail() );
			stmt.setObject( 4, participant.getNumTel() );
			stmt.setObject( 5, participant.getDateNaissance() );
			stmt.setObject( 6, participant.getLogin() );
			stmt.setObject( 7, participant.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

	}

	
	public void supprimer(int idParticipant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le Participant
			sql = "DELETE FROM participant WHERE id_utilisateur = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idParticipant );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Participant retrouver(int idParticipant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE id_utilisateur = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idParticipant);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipant(rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Participant> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Participant> participant = new ArrayList<>();
			while (rs.next()) {
				participant.add( construireParticipant(rs) );
			}
			return participant;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Participant construireParticipant( ResultSet rs ) throws SQLException {

		Participant participant = new Participant();
		participant.setId(rs.getObject( "id_utilisateur", Integer.class ));
		participant.setNom(rs.getObject( "nom", String.class ));
		participant.setPrenom(rs.getObject( "prenom", String.class ));
		participant.setE_Mail(rs.getObject( "e_mail", String.class ));
		participant.setNumTel(rs.getObject( "num_tel", String.class ));
		participant.setDateNaissance(rs.getObject("date_naissance", LocalDate.class));
		participant.setLogin(rs.getObject( "login", String.class ));

		return participant;
	}
}