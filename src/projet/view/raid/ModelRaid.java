package projet.view.raid;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoRaid;
import projet.data.Raid;

public class ModelRaid {

	private final ObservableList<Raid> liste = FXCollections.observableArrayList();
	

	private final Raid courant = new Raid();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoRaid daoRaid;

	// Getters

	public ObservableList<Raid> getListe() {
		return liste;
	}

	public Raid getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoRaid.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Raid());
	}
	

	public void preparerModifier(Raid item) {
		mapper.update(courant, daoRaid.retrouver(item.getId()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getNom_raid() == null || courant.getNom_raid().isEmpty()) {
			message.append("\nLe nom ne doit pas être vide.");
		} else if (courant.getNom_raid().length() > 50) {
			message.append("\nLe nom est trop long : 50 maxi.");
		}
		if (courant.getPrix_insc() == null || courant.getPrix_insc().isNaN()) {
			message.append("\nLe prix d'inscription ne peux pas être vide");
		} else if (courant.getPrix_insc() < 0) {
			message.append("\nLe prix ne peux pas être négatif.");
		}
		
		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId() == null) {
			// Insertion
			courant.setId(daoRaid.inserer(courant));
		} else {
			// modficiation
			daoRaid.modifier(courant);
		}
	}

	public void supprimer(Raid item) {

		daoRaid.supprimer(item.getId());
		mapper.update(courant, UtilFX.findNext(liste, item));
	}
}
