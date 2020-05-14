package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.ActionBenevole;


public class DaoActionBenevole {

	// Champs

	@Inject
	private DataSource		dataSource;
	

	
	// Actions

	public int inserer(ActionBenevole actionBenevole)  {
	
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le ActionBenevole
			sql = "INSERT INTO action_benevole ( id_poste,id_utilisateur, panneau_prendre, descr_action, signaleur, horaire_debut, horaire_fin) VALUES ( ?, ?, ?,?,?,?,?)";
			
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, actionBenevole.getId_poste() );
			stmt.setInt(2, actionBenevole.getId_utilisateur() );
			stmt.setObject(3, actionBenevole.getPanneau_prendre());
			stmt.setObject(4,actionBenevole.getDescr_action());
			stmt.setObject(5,actionBenevole.getSignaleur());
			stmt.setObject(6, actionBenevole.getHoraire_debut());
			stmt.setObject(7, actionBenevole.getHoraire_fin());
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			rs.next();
			actionBenevole.setId_action( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


		
		// Retourne l'identifiant
		return actionBenevole.getId_action();
	}

	
	public void modifier(ActionBenevole actionBenevole)  {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le ActionBenevole
			sql = "UPDATE action_benevole SET id_poste = ?,id_utilisateur = ?, panneau_prendre = ?, descr_action = ?, signaleur = ?, horaire_debut = ?, horaire_fin = ? WHERE id_action  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, actionBenevole.getId_poste() );
			stmt.setInt(2, actionBenevole.getId_utilisateur() );
			stmt.setBoolean(3, actionBenevole.getPanneau_prendre());
			stmt.setObject(4,actionBenevole.getDescr_action());
			stmt.setBoolean(5,actionBenevole.getSignaleur());
			stmt.setObject(6, actionBenevole.getHoraire_debut());
			stmt.setObject(7, actionBenevole.getHoraire_fin());
			stmt.setInt(8, actionBenevole.getId_action() );
			stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}


	}

	
	public void supprimer(int idActionBenevole)  {
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le ActionBenevole
			sql = "DELETE FROM action_benevole WHERE id_utilisateur = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idActionBenevole );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public ActionBenevole retrouver(int idActionBenevole)  {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM action_benevole WHERE id_action = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idActionBenevole);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireActionBenevole(rs);
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<ActionBenevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM action_benevole";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<ActionBenevole> actionBenevoleLst = new ArrayList<>();
			while (rs.next()) {
				actionBenevoleLst.add( construireActionBenevole(rs) );
			}
			return actionBenevoleLst;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private ActionBenevole construireActionBenevole( ResultSet rs) throws SQLException {
		ActionBenevole actionBenevole = new ActionBenevole();
		actionBenevole.setId_action(rs.getObject( "id_action", Integer.class ));
		actionBenevole.setId_poste(rs.getObject( "id_poste", Integer.class ));
		actionBenevole.setId_utilisateur(rs.getObject("id_utilisateur",Integer.class));
		actionBenevole.setPanneau_prendre(rs.getObject("panneau_prendre", Boolean.class));
		actionBenevole.setDescr_action(rs.getObject("descr_action",String.class));
		actionBenevole.setSignaleur(rs.getObject("signaleur", Boolean.class));
		actionBenevole.setHoraire_debut(rs.getObject("horaire_debut",LocalTime.class));
		actionBenevole.setHoraire_fin(rs.getObject("horaire_fin",LocalTime.class));
		
		return actionBenevole;
	}
}
