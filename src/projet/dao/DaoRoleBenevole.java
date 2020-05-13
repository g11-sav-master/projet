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
import projet.data.RoleBenevole;

public class DaoRoleBenevole {

	// Champs

	@Inject
	private DataSource dataSource;

	// Actions

	public int inserer(RoleBenevole rolebenevole) {
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Role_Benevole
			sql = "INSERT INTO role_benevole (nom_role) VALUES (?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setString(1, rolebenevole.getNom_role());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			rolebenevole.setId_role(rs.getObject(1, Integer.class));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

		// Retourne l'identifiant
		return rolebenevole.getId_role();
	}

	public void modifier(RoleBenevole rolebenevole) {
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le Role_Benevole
			sql = "UPDATE role_benevole SET nom_role = ? WHERE id_role  =  ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, rolebenevole.getNom_role());
			stmt.setObject(2, rolebenevole.getId_role());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

	}

	public void supprimer(int idRoleBenevole) {
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le Role_Benevole
			sql = "DELETE FROM role_benevole WHERE id_role = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idRoleBenevole);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public RoleBenevole retrouver(int idRoleBenevole) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM role_benevole WHERE id_role = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idRoleBenevole);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireRoleBenevole(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<RoleBenevole> listerTout() {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM role_benevole";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<RoleBenevole> rolebenevole = new ArrayList<>();
			while (rs.next()) {
				rolebenevole.add(construireRoleBenevole(rs) );
			}
			return rolebenevole;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private RoleBenevole construireRoleBenevole(ResultSet rs) throws SQLException {

		RoleBenevole rolebenevole = new RoleBenevole();
		rolebenevole.setId_role(rs.getObject("id_role", Integer.class));
		rolebenevole.setNom_role(rs.getObject("nom_role", String.class));
		return rolebenevole;
	}
}
