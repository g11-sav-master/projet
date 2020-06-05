package projet.view.equipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCategorieRaid;
import projet.dao.DaoFormeDuo;
import projet.dao.DaoParticipant;
import projet.dao.DaoParticipantDuo;
import projet.dao.DaoRaid;
import projet.data.CategorieRaid;
import projet.data.FormeDuo;
import projet.data.Participant;
import projet.data.ParticipantDuo;
import projet.data.Raid;

public class ModelEquipe {
	
	// Propriétés
	
	private final ObservableList<ParticipantDuo> liste = FXCollections.observableArrayList();
	private final ParticipantDuo courant = new ParticipantDuo();
	private Participant capitaine;
	private Participant equipier;
	
	// Autres champs
	
	@Inject
	private DaoParticipant daoParticipant;
	@Inject
	private DaoFormeDuo daoFormeDuo;
	@Inject
	private DaoParticipantDuo daoParticipantDuo;
	@Inject
	private DaoRaid daoRaid;
	@Inject
	private DaoCategorieRaid daoCategorieRaid;
	@Inject
	private IMapper mapper;
	
	// Getters
	
	public ObservableList<ParticipantDuo> getListe()
	{
		liste.setAll(daoParticipantDuo.listerTout());
		return liste;
	}
	
	public ObservableList<Participant> getParticipantPourUnRaid(int id_raid)
	{
		ArrayList<FormeDuo> listeFormeDuos = new ArrayList<FormeDuo>();
		listeFormeDuos.addAll(daoFormeDuo.listerTout());
		
		ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
		listeParticipants.addAll(daoParticipant.listerTout());
		Participant courantParticipant = new Participant();
		ParticipantDuo courantParticipantDuo = new ParticipantDuo();
		
		ArrayList<Participant> participantSansEquipe = new ArrayList<Participant>();
		for(FormeDuo formeDuo : listeFormeDuos)
		{
			courantParticipantDuo = daoParticipantDuo.retrouver(formeDuo.getIdPartDuo());
			courantParticipant = daoParticipant.retrouver(formeDuo.getIdUtilisateur());
			if(!listeParticipants.contains(courantParticipant) || courantParticipantDuo.getId_raid().getId() != id_raid) {
				participantSansEquipe.add(courantParticipant);
			}
		}
		return FXCollections.observableArrayList(participantSansEquipe);
	}
	
	public ObservableList<Raid> getRaids()
	{
		ArrayList<Raid> listRaids = new ArrayList<Raid>();
		listRaids.addAll(daoRaid.listerTout());
		return FXCollections.observableArrayList(listRaids);
	}
	
	public List<CategorieRaid> getCategorieRaids()
	{
		ArrayList<CategorieRaid> listCategorieRaids = new ArrayList<CategorieRaid>();
		listCategorieRaids.addAll(daoCategorieRaid.listerTout());
		return listCategorieRaids;
	}
	
	// Methodes
	
	public void creationEquipe(Participant capitaine, Participant equipier, Raid raid, CategorieRaid categorieRaid, String nombreRepas, boolean paiementValide) {
		ParticipantDuo participantDuo = new ParticipantDuo();
		if(categorieRaid == null) {
			throw new ExceptionValidation("Categorie du raid non sélectionné");
		}
		participantDuo.setId_categorie(categorieRaid.getIdCategorieRaid());
		if(raid == null) {
			throw new ExceptionValidation("Raid non sélectionné");
		}
		participantDuo.setId_raid(raid);
		try {
			int i = Integer.parseInt(nombreRepas);
			if(i<0) {
				throw new ExceptionValidation("Erreur dans le nombre de repas");
			}
			participantDuo.setNbr_repas(i);
		} catch (Exception e) {
			throw new ExceptionValidation("Erreur dans le nombre de repas");
		}
		participantDuo.setPaiement_valide(paiementValide);
		int idPartDuo = daoParticipantDuo.inserer(participantDuo);
		
		FormeDuo formeDuoCap = new FormeDuo();
		formeDuoCap.setEstCapitaine(true);
		formeDuoCap.setIdPartDuo(idPartDuo);
		if(capitaine == null) {
			throw new ExceptionValidation("Capitaine non sélectionné");
		}
		formeDuoCap.setIdUtilisateur(daoParticipant.retrouver(capitaine.getIdUtilisateur()));
		daoFormeDuo.inserer(formeDuoCap);
		
		FormeDuo formeDuoEq = new FormeDuo();
		formeDuoEq.setEstCapitaine(false);
		formeDuoEq.setIdPartDuo(idPartDuo);
		if(equipier == null) {
			throw new ExceptionValidation("Equipier non sélectionné");
		}
		formeDuoEq.setIdUtilisateur(daoParticipant.retrouver(equipier.getIdUtilisateur()));
		daoFormeDuo.inserer(formeDuoEq);
		
	}
	
	public void preparerModifier(ParticipantDuo item) {
		mapper.update(courant, daoParticipantDuo.retrouver(item.getId_part_duo()));
	}
	
	public void preparerAjouter() {
		 mapper.update(courant, new 
				 ParticipantDuo());
	}
	
	public void supprimer(ParticipantDuo item) {

		if (daoFormeDuo.retrouverId(item.getId_part_duo()) != null) {
			daoFormeDuo.supprimerCat(item.getId_part_duo());
			daoParticipantDuo.supprimer(item.getId_part_duo());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}
		else
		{
			daoParticipantDuo.supprimer(item.getId_part_duo());
			System.out.println(UtilFX.findNext(liste, item));
			mapper.update(courant, UtilFX.findNext(liste, item));
		}

	}

	public void actualiserListe() {
		liste.setAll(daoParticipantDuo.listerTout());
	}

	public ParticipantDuo getCourant() {
		return courant;
	}
	
	public void majParticipants(Participant capitaine, Participant equipier) {
		FormeDuo formeDuoCap = new FormeDuo();
		formeDuoCap.setEstCapitaine(true);
		formeDuoCap.setIdUtilisateur(capitaine);
		formeDuoCap.setIdPartDuo(courant.getId_part_duo());
		daoFormeDuo.modifier(formeDuoCap);
		
		FormeDuo formeDuoEq = new FormeDuo();
		formeDuoEq.setEstCapitaine(false);
		formeDuoEq.setIdUtilisateur(equipier);
		formeDuoEq.setIdPartDuo(courant.getId_part_duo());
		daoFormeDuo.modifier(formeDuoEq);
		
	}

	public void validerMiseAJour(Participant cap, Participant eq) {
		
		StringBuilder message = new StringBuilder();
		
		if(courant.getPaiement_valide() == null)
			message.append("\nLe paiement doit être défini.");
		if(courant.getId_categorie() == null)
			message.append("\nLa catégorie de raid doit être défini.");
		if(courant.getId_raid() == null)
			message.append("\nLe raid doit être défini.");
		if(courant.getNbr_repas() == null)
			message.append("\nLe nombre de repas doit être défini.");
		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
		
		if(courant.getId_part_duo() != null) {
			daoParticipantDuo.modifier(courant);
			majParticipants(cap, eq);
		}	
	}
	
}