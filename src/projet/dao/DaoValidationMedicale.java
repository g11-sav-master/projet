package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.ValidationMedicale;

public class DaoValidationMedicale {

	// Champs

	@Inject
	private DataSource dataSource;

	@Inject
	private DaoParticipant daoParticipant;

	// Actions

	public int inserer(ValidationMedicale validation) {
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Insère la validation médicale
			if (daoParticipant.retrouver(validation.getParticipant().getIdUtilisateur()) != null) {
				sql = "INSERT INTO validation_medicale ( id_utilisateur, date_expiration) VALUES ( ?, ?)";
				// Vérif
				stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, validation.getParticipant().getIdUtilisateur());
				stmt.setObject(2, validation.getDate_expiration());
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				validation.setId_validation(rs.getObject(1, Integer.class));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

		// Retourne l'identifiant
		return validation.getId_validation();
	}

	public void modifier(ValidationMedicale validation) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			if (daoParticipant.retrouver(validation.getParticipant().getIdUtilisateur()) != null) {
				cn = dataSource.getConnection();
				// Modifie la validation
				sql = "UPDATE validation_medicale SET date_expiration = ? WHERE id_validation  =  ? AND id_utilisateur = ?";
				stmt = cn.prepareStatement(sql);
				stmt.setObject(1, validation.getDate_expiration());
				stmt.setObject(2, validation.getId_validation());
				stmt.setObject(3, validation.getParticipant().getIdUtilisateur());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

	}

	public void supprimer(int idValidation) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Supprime la validation
			sql = "DELETE FROM validation_medicale WHERE id_validation = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idValidation);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public ValidationMedicale retrouver(int idValidation) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM validation_medicale WHERE id_validation = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idValidation);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireValidationMedicale(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}
	
	public ValidationMedicale retrouverUtilisateur(int idUtilisateur)
	{
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM validation_medicale WHERE id_utilisateur = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idUtilisateur);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireValidationMedicale(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<ValidationMedicale> listerTout() {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM validation_medicale";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<ValidationMedicale> validationLst = new ArrayList<>();
			while (rs.next()) {
				validationLst.add(construireValidationMedicale(rs));
			}
			return validationLst;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private ValidationMedicale construireValidationMedicale(ResultSet rs) throws SQLException {

		ValidationMedicale validation = new ValidationMedicale();
		validation.setId_validation(rs.getObject("id_validation", Integer.class));
		Integer idP = rs.getObject("id_utilisateur", Integer.class);
		if ( idP != null)
		{
			validation.setParticipant(daoParticipant.retrouver(idP));
		}
		validation.setDate_expiration(rs.getObject("date_expiration", LocalDate.class));
		validation.setValide();
		return validation;
	}
}
