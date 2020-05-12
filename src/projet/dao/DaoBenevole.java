package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Inject
	private DaoUtilisateur  daoUtilisateur;

	
	// Actions

	public int inserer(Benevole benevole)  {
		benevole.setIdUtilisateur(daoUtilisateur.inserer(benevole)); 
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Benevole
			sql = "INSERT INTO benevole ( id_utilisateur, id_role, possede_permis) VALUES ( ?, ?, ?)";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, benevole.getIdUtilisateur() );
			stmt.setInt(2, benevole.getId_role() );
			stmt.setBoolean(3, benevole.getPossedePermis());
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return benevole.getIdUtilisateur();
	}

	
	public void modifier(Benevole benevole)  {
		daoUtilisateur.modifier(benevole);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le Benevole
			sql = "UPDATE benevole SET id_role = ?, possede_permis = ? WHERE id_utilisateur  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.getId_role() );
			stmt.setObject( 2, benevole.getPossedePermis() );
			stmt.setObject( 3, benevole.getIdUtilisateur() );
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
		daoUtilisateur.supprimer(idBenevole);
	}

	
	public Benevole retrouver(int idBenevole)  {
		Benevole benevole = new Benevole( daoUtilisateur.retrouver(idBenevole));
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
                return construireBenevole(rs, benevole );
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

			sql = "SELECT * FROM benevole";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoleLst = new ArrayList<>();
			while (rs.next()) {
				Benevole benevole = new Benevole( daoUtilisateur.retrouver(rs.getObject( "id_utilisateur", Integer.class )));
				benevoleLst.add( construireBenevole(rs, benevole) );
			}
			return benevoleLst;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Benevole construireBenevole( ResultSet rs, Benevole benevole ) throws SQLException {

		benevole.setId_role(rs.getObject( "id_role", Integer.class ));
		benevole.setPossedePermis(rs.getObject( "possede_permis", Boolean.class ));

		return benevole;
	}
}
