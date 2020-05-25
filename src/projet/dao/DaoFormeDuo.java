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
import projet.data.FormeDuo;

public class DaoFormeDuo {

	// Champs

	@Inject
	private DataSource dataSource;

	@Inject
	private DaoParticipantDuo daoParticipantDuo;
	@Inject
	private DaoParticipant daoParticipant;

	// Actions

	public void inserer(FormeDuo formeDuo) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			if ((daoParticipantDuo.retrouver(formeDuo.getIdPartDuo()) != null)
					&& daoParticipant.retrouver(formeDuo.getIdUtilisateur()) != null) {
				cn = dataSource.getConnection();
				sql = "INSERT INTO forme_duo ( id_part_duo, id_utilisateur, est_capitaine) VALUES ( ?, ?, ?)";
				stmt = cn.prepareStatement(sql);
				stmt.setInt(1, formeDuo.getIdPartDuo());
				stmt.setInt(2, formeDuo.getIdUtilisateur());
				stmt.setBoolean(3, formeDuo.getEstCapitaine());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

		// Retourne l'identifiant
	}

	public void modifier(FormeDuo formeDuo) {
		
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			if ((daoParticipantDuo.retrouver(formeDuo.getIdPartDuo()) != null)
					&& daoParticipant.retrouver(formeDuo.getIdUtilisateur()) != null) {
				cn = dataSource.getConnection();
				sql = "UPDATE forme_duo SET est_capitaine = ? WHERE id_utilisateur  =  ? AND id_part_duo = ?";
				stmt = cn.prepareStatement(sql);
				stmt.setBoolean(1, formeDuo.getEstCapitaine());
				stmt.setInt(2, formeDuo.getIdUtilisateur());
				stmt.setInt(3, formeDuo.getIdPartDuo());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public void supprimer(int idPartDuo, int idUtilisateur) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM forme_duo WHERE id_part_duo = ? AND id_utilisateur = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idPartDuo);
			stmt.setInt( 2, idUtilisateur);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public FormeDuo retrouver(int idPartDuo, int idUtilisateur) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM forme_duo WHERE id_part_duo = ? AND id_utilisateur = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idPartDuo );
			stmt.setInt( 2, idUtilisateur );
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireFormeDuo(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<FormeDuo> listerTout() {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM forme_duo";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<FormeDuo> formeDuoList = new ArrayList<>();
			while (rs.next()) {
				formeDuoList.add(construireFormeDuo(rs));
			}
			return formeDuoList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private FormeDuo construireFormeDuo(ResultSet rs) throws SQLException {

		FormeDuo formeDuo = new FormeDuo();
		formeDuo.setIdUtilisateur(rs.getObject("id_utilisateur", Integer.class));
		formeDuo.setIdPartDuo(rs.getObject("id_part_duo", Integer.class));
		formeDuo.setEstCapitaine(rs.getObject("est_capitaine", Boolean.class));
		
		return formeDuo;
	}
	
	public void supprimerCat(int idPartDuo) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM forme_duo WHERE id_part_duo IN (SELECT id_part_duo FROM participant_duo WHERE id_categorie = ?)";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idPartDuo);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}
}
