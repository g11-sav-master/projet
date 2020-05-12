package projet.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	@Inject
	private DaoUtilisateur daoUtilisateur;

	
	// Actions

	public int inserer(Participant participant)  {
		participant.setIdUtilisateur(daoUtilisateur.inserer(participant));
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Participant
			sql = "INSERT INTO participant ( id_utilisateur, club) VALUES ( ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setInt( 1, participant.getIdUtilisateur() );
			stmt.setString(2,  participant.getClub());
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return participant.getIdUtilisateur();
	}

	
	public void modifier(Participant participant)  {
		daoUtilisateur.modifier(participant);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le Participant
			sql = "UPDATE participant SET club = ?  WHERE id_utilisateur  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, participant.getClub() );
			stmt.setObject( 2, participant.getIdUtilisateur() );
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
		daoUtilisateur.supprimer(idParticipant);
	}

	
	public Participant retrouver(int idParticipant)  {
		Participant participant = new Participant( daoUtilisateur.retrouver(idParticipant));
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
                return construireParticipant(rs, participant );
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

			sql = "SELECT * FROM participant";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Participant> participantLst = new ArrayList<>();
			while (rs.next()) {
				Participant participant = new Participant( daoUtilisateur.retrouver(rs.getObject( "id_utilisateur", Integer.class )));
				participantLst.add( construireParticipant(rs, participant) );
			}
			return participantLst;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Participant construireParticipant( ResultSet rs, Participant participant ) throws SQLException {

		participant.setClub(rs.getObject( "club", String.class ));

		return participant;
	}
}