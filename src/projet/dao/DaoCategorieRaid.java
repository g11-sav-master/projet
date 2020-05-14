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
import projet.data.CategorieRaid;


public class DaoCategorieRaid {

	// Champs

	@Inject
	private DataSource		dataSource;

	// Actions

	public int insererCategorieRaid( CategorieRaid categorieRaid )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère la catégorie
			sql = "INSERT INTO categorie_raid ( nom_categorie) VALUES ( ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(	1, categorieRaid.getNomCategorie() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			categorieRaid.setIdCategorieRaid( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		return categorieRaid.getIdCategorieRaid();
	}
	
	public void modifierCategorieRaid(CategorieRaid categorieRaid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie la catégorie
			sql = "UPDATE categorie_raid SET nom_categorie = ? WHERE id_categorie  =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, categorieRaid.getNomCategorie() );
			stmt.setObject( 2,  categorieRaid.getIdCategorieRaid() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimerCategorieRaid( int idCategorieRaid ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les roles
			sql = "DELETE FROM categorie_raid  WHERE id_categorie = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idCategorieRaid );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	public CategorieRaid retrouverCategorieRaid(int idCategorieRaid)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM categorie_raid WHERE id_categorie = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idCategorieRaid);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireCategorieRaid(rs);
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	public List<CategorieRaid> listerTout() {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM categorie_raid";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<CategorieRaid> categoriesRaid = new ArrayList<>();
			while (rs.next()) {
				categoriesRaid.add( construireCategorieRaid(rs) );
			}
			return categoriesRaid;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	// Méthodes auxiliaires
	
	private CategorieRaid construireCategorieRaid(ResultSet rs) throws SQLException {
		CategorieRaid categorieRaid = new CategorieRaid();
		categorieRaid.setIdCategorieRaid(rs.getObject( "id_categorie", Integer.class ));
		categorieRaid.setNomCategorie(rs.getObject( "nom_categorie", String.class ));
		return categorieRaid;
	}

}
