package projet.view.action_benevole;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoActionBenevole;
import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.benevole.ModelBenevole;
import projet.view.poste.ModelPoste;

public class ModelActionBenevole {

	private final ObservableList<ActionBenevole> liste = FXCollections.observableArrayList();
	

	private final ActionBenevole courant = new ActionBenevole();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoActionBenevole daoActionBenevole;
	@Inject 
	private ModelBenevole modelBenevole;
	@Inject 
	private ModelPoste modelPoste;
	// Getters

	public ObservableList<ActionBenevole> getListe() {
		return liste;
	}
	public ObservableList<Benevole> getBenevoles(){
		return modelBenevole.getListe();
	}
	public ObservableList<Poste> getPostes(){
		return modelPoste.getListe();
	}

	public ActionBenevole getCourant() {
		return courant;
	}
	
	
	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoActionBenevole.listerTout());
	}

	
	// Actions

	public void preparerAjouter() {
		modelBenevole.actualiserListe();
		modelPoste.actualiserListe();
		mapper.update(courant, new ActionBenevole());
	}
	

	public void preparerModifier(ActionBenevole item) {
		modelBenevole.actualiserListe();
		modelPoste.actualiserListe();
		mapper.update(courant, daoActionBenevole.retrouver(item.getId_action()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getPoste() == null)  {
			message.append("\nLe poste doit être défini.");
		}
		if (courant.getBenevole() == null ) {
			message.append("\nLe bénévole doit être défini");
		}
		
		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId_action() == null) {
			// Insertion
			courant.setId_action(daoActionBenevole.inserer(courant));
		} else {
			// modficiation
			daoActionBenevole.modifier(courant);
		}
	}

	public void supprimer(ActionBenevole item) {

		daoActionBenevole.supprimer(item.getId_action());
		System.out.println(UtilFX.findNext(liste, item));
		mapper.update(courant, UtilFX.findNext(liste, item));
	}
}
