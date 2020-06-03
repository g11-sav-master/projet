package projet.view.equipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		participantDuo.setId_categorie(categorieRaid.getIdCategorieRaid());
		participantDuo.setId_raid(raid);
		participantDuo.setNbr_repas(Integer.parseInt(nombreRepas));
		participantDuo.setPaiement_valide(paiementValide);
		
		int idPartDuo = daoParticipantDuo.inserer(participantDuo);
		
		FormeDuo formeDuoCap = new FormeDuo();
		formeDuoCap.setEstCapitaine(true);
		formeDuoCap.setIdPartDuo(idPartDuo);
		formeDuoCap.setIdUtilisateur(daoParticipant.retrouver(capitaine.getIdUtilisateur()));
		daoFormeDuo.inserer(formeDuoCap);
		
		FormeDuo formeDuoEq = new FormeDuo();
		formeDuoEq.setEstCapitaine(false);
		formeDuoEq.setIdPartDuo(idPartDuo);
		formeDuoEq.setIdUtilisateur(daoParticipant.retrouver(equipier.getIdUtilisateur()));
		daoFormeDuo.inserer(formeDuoEq);
		
	}
	
	public void preparerModifier(ParticipantDuo item) {
		mapper.update(courant, daoParticipantDuo.retrouver(item.getId_part_duo()));
	}
	
	public void preparerAjouter() {
		 mapper.update(courant, new ParticipantDuo());
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

	public void validerMiseAJour() {
		
		StringBuilder message = new StringBuilder();
		
		if(courant.getPaiement_valide() == null)
			message.append("\nLe paiement doit être défini.");
		if(courant.getId_categorie() == null)
			message.append("\nLa catégorie de raid doit être défini.");
		if(courant.getId_raid() == null)
			message.append("\nLe raid doit être défini.");
		if(courant.getNbr_repas() == null)
			message.append("\nLe nombre de repas doit être défini.");
	}
	
}