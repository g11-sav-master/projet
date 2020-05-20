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
import projet.data.Poste;

public class DaoPoste {
	
	@Inject
	private DataSource dataSource;
	
	//Insertion
	
		public int inserer(Poste poste)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				// Insère le poste
				sql = "INSERT INTO poste ( id_raid, nbr_benev) VALUES ( ?, ?)";
				stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
				stmt.setObject(	1, poste.getId_raid() );
				stmt.setObject(	2, poste.getNbr_benev() );
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				poste.setId_poste( rs.getObject( 1, Integer.class ) );
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
			// Retourne l'identifiant
			return poste.getId();
		}
		
		//modifier poste
		
		public void modifier(Poste poste)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie le poste
				sql = "UPDATE poste SET id_raid = ?, nbr_benev = ? WHERE id_poste  =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject( 1, poste.getId_raid() );
				stmt.setObject( 2, poste.getNbr_benev() );
				stmt.setObject( 3, poste.getId() );
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}

		}
		
		//supprimer
		
		public void supprimer(int idPoste)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;


			try {
				cn = dataSource.getConnection();

				// Supprime le poste
				sql = "DELETE FROM poste WHERE id_poste = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, idPoste );
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		//affichage
		public Poste retrouver(int idPoste)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM poste WHERE id_poste = ?";
	            stmt = cn.prepareStatement(sql);
	            stmt.setObject( 1, idPoste);
	            rs = stmt.executeQuery();

	            if ( rs.next() ) {
	                return construirePoste(rs );
	            } else {
	            	return null;
	            }
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		//lister
		public List<Poste> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM poste ;";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Poste> poste = new ArrayList<>();
				while (rs.next()) {
					poste.add( construirePoste(rs) );
				}
				return poste;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		//méthodes auxilliaires
		
		private Poste construirePoste( ResultSet rs ) throws SQLException {

			Poste poste = new Poste();
			poste.setId_poste(rs.getObject( "id_poste", Integer.class ));
			poste.setId_raid(rs.getObject( "id_raid", Integer.class ));
			poste.setNbr_benev(rs.getObject( "nbr_benev", Integer.class ));

			return poste;
		}


}
