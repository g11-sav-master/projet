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
import projet.data.ParticipantDuo;

public class DaoParticipantDuo {

	// Champs

	@Inject
	private DataSource dataSource;

	@Inject
	private DaoCategorieRaid daoCategorie;
	@Inject
	private DaoRaid daoRaid;

	// Actions

	public int inserer(ParticipantDuo partduo) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			if ((daoCategorie.retrouverCategorieRaid(partduo.getId_categorie()) != null)
					&& daoRaid.retrouver(partduo.getId_raid()) != null) {
				cn = dataSource.getConnection();
				sql = "INSERT INTO participant_duo ( id_categorie, id_raid, nbr_repas, paiement_valide) VALUES ( ?, ?, ?,?)";
				stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, partduo.getId_categorie());
				stmt.setInt(2, partduo.getId_raid());
				stmt.setInt(3, partduo.getNbr_repas());
				stmt.setBoolean(4, partduo.getPaiement_valide());
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				partduo.setId_part_duo(rs.getObject(1, Integer.class));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

		// Retourne l'identifiant
		return partduo.getId_part_duo();
	}

	public void modifier(ParticipantDuo partduo) {
		
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			if ((daoCategorie.retrouverCategorieRaid(partduo.getId_categorie()) != null)
					&& daoRaid.retrouver(partduo.getId_raid()) != null) {
				cn = dataSource.getConnection();
				sql = "UPDATE participant_duo SET id_categorie = ?, id_raid = ?, nbr_repas = ?, paiement_valide = ? WHERE id_part_duo  =  ?";
				stmt = cn.prepareStatement(sql);
				stmt.setObject(1, partduo.getId_categorie());
				stmt.setObject(2, partduo.getId_raid());
				stmt.setObject(3, partduo.getNbr_repas());
				stmt.setBoolean(4, partduo.getPaiement_valide());
				stmt.setObject(5, partduo.getId_part_duo());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}

	}

	public void supprimer(int idpartduo) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM participant_duo WHERE id_part_duo = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idpartduo);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public ParticipantDuo retrouver(int idpartduo) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant_duo WHERE id_part_duo = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idpartduo);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireParticipantDuo(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<ParticipantDuo> listerTout() {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant_duo";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<ParticipantDuo> partduoLst = new ArrayList<>();
			while (rs.next()) {
				partduoLst.add(construireParticipantDuo(rs));
			}
			return partduoLst;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private ParticipantDuo construireParticipantDuo(ResultSet rs) throws SQLException {

		ParticipantDuo participantduo = new ParticipantDuo();
		participantduo.setId_part_duo(rs.getObject("id_part_duo", Integer.class));
		participantduo.setId_categorie(rs.getObject("id_categorie", Integer.class));
		participantduo.setId_raid(rs.getObject("id_raid", Integer.class));
		participantduo.setNbr_repas(rs.getObject("nbr_repas", Integer.class));
		participantduo.setPaiement_valide(rs.getObject("paiement_valide", Boolean.class));

		return participantduo;
	}
}
