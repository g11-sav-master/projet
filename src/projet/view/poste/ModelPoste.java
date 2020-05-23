package projet.view.poste;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPoste;
import projet.dao.DaoActionBenevole;
import projet.dao.DaoRaid;
import projet.data.Poste;

public class ModelPoste {

	private final ObservableList<Poste> liste = FXCollections.observableArrayList();

	private final Poste courant = new Poste();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoPoste daoPoste;
	@Inject
	private DaoActionBenevole daoActionB;
	@Inject
	private DaoRaid daoRaid;

	// Getters

	public ObservableList<Poste> getListe() {
		return liste;
	}

	public Poste getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoPoste.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Poste());
	}

	public void preparerModifier(Poste item) {
		mapper.update(courant, daoPoste.retrouver(item.getId_poste()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getId_raid() == null || courant.getId_raid() == 0) {
			message.append("\nLe numero de raid ne peut pas être nul.");
		} else if (daoRaid.retrouver(courant.getId_raid()) == null) {
			message.append("\nLe raid n'existe pas");
		}
		if (courant.getNbr_benev() == null || courant.getNbr_benev() == 0) {
			message.append("\nLe nombre de benevole requis pour un poste ne peut pas valoir 0.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId_poste() == null) {
			// Insertion
			courant.setId_poste(daoPoste.inserer(courant));
		} else {
			// Modficiation
			daoPoste.modifier(courant);
		}
	}

	public void supprimer(Poste item) {

		if (daoActionB.retrouver(item.getId_poste()) != null) {
			daoActionB.supprimerPoste(item.getId_poste());
			daoPoste.supprimer(item.getId_poste());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}

	}
}
