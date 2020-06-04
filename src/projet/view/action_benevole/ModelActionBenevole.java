package projet.view.action_benevole;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoActionBenevole;
import projet.dao.DaoParticipeOrganisation;
import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.ParticipeOrganisation;
import projet.data.Poste;
import projet.view.benevole.ModelBenevole;
import projet.view.poste.ModelPoste;

public class ModelActionBenevole {

	private final ObservableList<ActionBenevole> liste = FXCollections.observableArrayList();
	

	private final ActionBenevole courant = new ActionBenevole();
	
	private  ParticipeOrganisation PO = new ParticipeOrganisation();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoActionBenevole daoActionBenevole;
	@Inject
	private DaoParticipeOrganisation daoPOrga;
	@Inject
	private DaoActionBenevole daoPoste;
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
	
	public ParticipeOrganisation getPO() {
		return PO;
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
		this.PO= new ParticipeOrganisation();
	}
	

	public void preparerModifier(ActionBenevole item) {
		modelBenevole.actualiserListe();
		modelPoste.actualiserListe();
		mapper.update(courant, daoActionBenevole.retrouver(item.getId_action()));
		mapper.update(PO, daoPOrga.retrouver(courant.getBenevole().getIdUtilisateur(), courant.getPoste().getId_raid()));
		//System.out.println(modelPoste.getCourant());
		modelPoste.preparerModifier(courant.getPoste());
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
		if(courant.getPanneau_prendre() == null)
			courant.setPanneau_prendre(false);
		if(courant.getSignaleur() == null)
			courant.setSignaleur(false);

		// Effectue la mise à jour

		if (courant.getId_action() == null) {
			// Insertion
			courant.setId_action(daoActionBenevole.inserer(courant));
		} else {
			// modficiation
			daoActionBenevole.modifier(courant);
			this.PO.setIdRaid(courant.getPoste().getId_raid());
			this.PO.setIdUtilisateur(courant.getBenevole().getIdUtilisateur());
			daoPOrga.modifier(this.PO);
		}
	}

	public void supprimer(ActionBenevole item) {

		daoActionBenevole.supprimer(item.getId_action());
		mapper.update(courant, UtilFX.findNext(liste, item));
	}
}
