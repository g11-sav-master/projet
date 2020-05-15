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
import projet.data.Raid;

public class DaoRaid {
	
	@Inject
	private DataSource		dataSource;
	
	//Insertion
	
	public int inserer(Raid raid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le raid
			sql = "INSERT INTO raid ( nom_raid, annee, prix_insc, prix_repas) VALUES ( ?, ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, raid.getNom_raid() );
			stmt.setObject(	2, raid.getAnnee() );
			stmt.setObject(	3, raid.getPrix_insc() );
			stmt.setObject(	4, raid.getPrix_repas() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			raid.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		// Retourne l'identifiant
		return raid.getId();
	}
	
	//modifier raid
	
	public void modifier(Raid raid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le raid
			sql = "UPDATE raid SET nom_raid = ?, annee = ?, prix_insc = ?, prix_repas = ? WHERE id_raid  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString( 1, raid.getNom_raid() );
			stmt.setObject( 2, raid.getAnnee() );
			stmt.setObject( 3, raid.getPrix_insc() );
			stmt.setObject( 4, raid.getPrix_repas() );
			stmt.setObject( 5,  raid.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

	}
	
	//supprimer
	
	public void supprimer(int idRaid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le raid
			sql = "DELETE FROM raid WHERE id_raid = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idRaid );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	//affichage
	public Raid retrouver(int idRaid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM raid WHERE id_raid = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idRaid);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireRaid(rs );
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
	public List<Raid> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM raid ;";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Raid> raid = new ArrayList<>();
			while (rs.next()) {
				raid.add( construireRaid(rs) );
			}
			return raid;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	//méthodes auxilliaires
	
	private Raid construireRaid( ResultSet rs ) throws SQLException {

		Raid raid = new Raid();
		raid.setId(rs.getObject( "id_raid", Integer.class ));
		raid.setNom_raid(rs.getObject( "nom_raid", String.class ));
		raid.setAnnee(rs.getObject( "annee", LocalDate.class ));
		raid.setPrix_insc(rs.getObject( "prix_insc", Double.class ));
		raid.setPrix_repas(rs.getObject( "prix_repas", Double.class ));

		return raid;
	}

}
