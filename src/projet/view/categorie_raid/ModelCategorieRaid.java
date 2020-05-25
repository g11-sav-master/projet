package projet.view.categorie_raid;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCategorieRaid;
import projet.data.CategorieRaid;
import projet.dao.DaoParticipantDuo;
import projet.dao.DaoFormeDuo;

public class ModelCategorieRaid {

	private final ObservableList<CategorieRaid> liste = FXCollections.observableArrayList();

	private final CategorieRaid courant = new CategorieRaid();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoCategorieRaid daoCategorieRaid;
	@Inject
	private DaoParticipantDuo daoParticipantDuo;
	@Inject
	private DaoFormeDuo daoFormeDuo;

	// Getters

	public ObservableList<CategorieRaid> getListe() {
		return liste;
	}

	public CategorieRaid getCourant() {
		return courant;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoCategorieRaid.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new CategorieRaid());
	}

	public void preparerModifier(CategorieRaid item) {
		mapper.update(courant, daoCategorieRaid.retrouverCategorieRaid(item.getIdCategorieRaid()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getNomCategorie() == null || courant.getNomCategorie().isEmpty()) {
			message.append("\nLe nom de la catégorie raid ne peut pas être nul.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getIdCategorieRaid() == null) {
			// Insertion
			courant.setIdCategorieRaid(daoCategorieRaid.insererCategorieRaid(courant));
		} else {
			// Modficiation
			daoCategorieRaid.modifierCategorieRaid(courant);
		}
	}

	public void supprimer(CategorieRaid item) {

		if (daoParticipantDuo.retrouverCat(item.getIdCategorieRaid()) != null) {
			daoFormeDuo.supprimerCat(item.getIdCategorieRaid());
			daoParticipantDuo.supprimerCat(item.getIdCategorieRaid());
			daoCategorieRaid.supprimerCategorieRaid(item.getIdCategorieRaid());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}
		else
		{
			daoCategorieRaid.supprimerCategorieRaid(item.getIdCategorieRaid());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}

	}
}
