package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Compte;


public class DaoCompte {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	

	

	


	public Compte validerAuthentification( String pseudo, String motDePasse )  {
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE login = ? AND mot_passe = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, pseudo );
			stmt.setObject( 2, motDePasse );
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireCompte( rs );			
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	// Méthodes auxiliaires
	
	private Compte construireCompte( ResultSet rs ) throws SQLException {
		Compte compte = new Compte();
		compte.setId( rs.getObject( "id_utilisateur", Integer.class ) );
		compte.setPseudo( rs.getObject( "login", String.class ) );
		compte.setMotDePasse( rs.getObject( "mot_passe", String.class ) );
		compte.setEmail( rs.getObject( "e_mail", String.class ) );
		
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs2 		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM role_benevole WHERE id_role = (SELECT id_role FROM benevole WHERE id_utilisateur = ? ) ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, compte.getId().intValue() );
			rs2 = stmt.executeQuery();

			if ( rs2.next() ) {
				compte.getRoles().setAll( rs2.getObject( "nom_role", String.class ) );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs2, stmt, cn );
		}
		
		return compte;
	}
}
