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
import projet.data.ParticipeOrganisation;


public class DaoParticipeOrganisation {

	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoRaid			daoRaid;
	@Inject
	private DaoBenevole		daoBenevole;

	// Actions

	public void insererParticipeOrganisation( ParticipeOrganisation participeOrganisation )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			
			if ((daoRaid.retrouver(participeOrganisation.getIdRaid()) != null)
					&& daoBenevole.retrouver(participeOrganisation.getIdUtilisateur()) != null) {
				
				cn = dataSource.getConnection();
	
				// Insère la catégorie
				sql = "INSERT INTO participe_organisation ( id_raid, id_utilisateur, nbr_repas ) VALUES ( ?, ?, ? )";
				stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
				stmt.setInt(	1, participeOrganisation.getIdRaid());
				stmt.setInt(	2, participeOrganisation.getIdUtilisateur());
				stmt.setInt(	3, participeOrganisation.getNbreRepas());
				stmt.executeUpdate();
			}
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public void modifierParticipationOrganisation(ParticipeOrganisation participeOrganisation)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			if ((daoRaid.retrouver(participeOrganisation.getIdRaid()) != null)
					&& daoBenevole.retrouver(participeOrganisation.getIdUtilisateur()) != null) {
				cn = dataSource.getConnection();
	
				// Modifie la catégorie
				sql = "UPDATE participe_organisation SET nbr_repras = ? WHERE id_utilisateur  =  ? AND id_raid = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setInt( 1, participeOrganisation.getNbreRepas() );
				stmt.setInt( 2, participeOrganisation.getIdUtilisateur() );
				stmt.setInt( 3, participeOrganisation.getIdRaid() );
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimerCategorieRaid( int idUtilisateur, int idRaid ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les roles
			sql = "DELETE FROM participe_organisation  WHERE id_utilisateur = ? AND id_raid = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idUtilisateur );
			stmt.setInt( 2, idRaid);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
public void modifier(ParticipeOrganisation participeOrganisation) {
		
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			if ((daoRaid.retrouver(participeOrganisation.getIdRaid()) != null)
					&& daoBenevole.retrouver(participeOrganisation.getIdUtilisateur()) != null) {
				cn = dataSource.getConnection();
				sql = "UPDATE participe_organisation SET nbr_repas = ? WHERE id_utilisateur  =  ? AND id_raid = ?";
				stmt = cn.prepareStatement(sql);
				stmt.setInt(1, participeOrganisation.getNbreRepas());
				stmt.setInt(2, participeOrganisation.getIdUtilisateur());
				stmt.setInt(3, participeOrganisation.getIdRaid());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public ParticipeOrganisation retrouver(int idUtilisateur, int idRaid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participe_organisation WHERE id_utilisateur = ? AND id_raid = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setInt( 1, idUtilisateur );
            stmt.setInt( 2, idRaid );
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipeOrganisation(rs);
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	public List<ParticipeOrganisation> listerTout() {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participe_organisation";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<ParticipeOrganisation> participeOrganisationList = new ArrayList<>();
			while (rs.next()) {
				participeOrganisationList.add( construireParticipeOrganisation(rs) );
			}
			return participeOrganisationList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	// Méthodes auxiliaires
	
	private ParticipeOrganisation construireParticipeOrganisation(ResultSet rs) throws SQLException {
		ParticipeOrganisation participeOrganisation = new ParticipeOrganisation();
		participeOrganisation.setIdUtilisateur(rs.getObject( "id_utilisateur", Integer.class ));
		participeOrganisation.setIdRaid(rs.getObject( "id_raid", Integer.class ));
		participeOrganisation.setNbreRepas(rs.getObject( "nbr_repas", Integer.class ));
		return participeOrganisation;
	}

}
