package projet.view.accueil;

import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.dao.DaoActionBenevole;
import projet.dao.DaoFormeDuo;
import projet.dao.DaoParticipantDuo;
import projet.dao.DaoParticipeOrganisation;
import projet.dao.DaoPoste;
import projet.dao.DaoRaid;
import projet.dao.DaoParticipant;
import projet.dao.DaoValidationMedicale;
import projet.data.ActionBenevole;
import projet.data.FormeDuo;
import projet.data.Participant;
import projet.data.ParticipantDuo;
import projet.data.ParticipeOrganisation;
import projet.data.Poste;
import projet.data.Raid;
import projet.data.ValidationMedicale;

public class ModelAccueil {
	
	// TODO : curseur invisible tab accueil
	
	private ObservableList<Participant> liste = FXCollections.observableArrayList();
	
	private final Participant courant = new Participant();
	
	// Autres champs
	
	@Inject
	private DaoParticipant daoParticipant;
	@Inject
	private DaoFormeDuo daoFormeDuo;
	@Inject
	private DaoValidationMedicale daoValidationMedicale;
	@Inject
	private DaoParticipantDuo daoParticipantDuo;
	@Inject
	private DaoParticipeOrganisation daoParticipeOrganisation;
	@Inject
	private DaoRaid daoRaid;
	@Inject
	private DaoActionBenevole daoActionBenevole;
	@Inject
	private DaoPoste daoPoste;
	
	// Getters
	
	public ObservableList<Participant> getListe() {
		return liste;
	}

	public Participant getCourant() {
		return courant;
	}
	
	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoParticipant.listerTout());
	}
	
	// 
	
	public ObservableList<Participant> listerDossierAttentes()
	{
		ArrayList<Participant> participants = new ArrayList<Participant>();
		ObservableList<Participant> dossierEnAttente = FXCollections.observableArrayList();
		participants.addAll(daoParticipant.listerTout());
		for(Participant participant : participants)
		{
			ValidationMedicale validationMedicale = daoValidationMedicale.retrouverUtilisateur(participant.getIdUtilisateur());
			FormeDuo formeDuo = daoFormeDuo.retrouverUtilisateur(participant.getIdUtilisateur());
			ParticipantDuo participantDuo = daoParticipantDuo.retrouver(formeDuo.getIdPartDuo());
			
			if(!validationMedicale.getEst_valide() || !participantDuo.getPaiement_valide())
			{
				dossierEnAttente.add(participant);
			}
		}
		
		return dossierEnAttente;
	}
	
	public ObservableList<String> listerNbrRepas()
	{
		int nbRepasBenevole=0, nbRepasParticipant=0;
		ArrayList<ParticipeOrganisation> listParticipeOrganisation = new ArrayList<ParticipeOrganisation>();
		listParticipeOrganisation.addAll(daoParticipeOrganisation.listerTout());
		for(ParticipeOrganisation participeOrganisation : listParticipeOrganisation)
		{
			nbRepasBenevole+=participeOrganisation.getNbreRepas();
		}
		
		ArrayList<ParticipantDuo> listParticipantDuos = new ArrayList<ParticipantDuo>();
		listParticipantDuos.addAll(daoParticipantDuo.listerTout());
		
		for(ParticipantDuo participantDuo : listParticipantDuos)
		{
			nbRepasParticipant+=participantDuo.getNbr_repas();
		}
		
		ObservableList<String> listes = FXCollections.observableArrayList();
		listes.add("Bénévoles : "+nbRepasBenevole+" repas commandé(s)");
		listes.add("Participants : "+nbRepasParticipant+" repas commandé(s)");
		return listes;
	}
	
	public ObservableList<String> listerNbrPersonnesParRaid()
	{
		ArrayList<Raid> raids = new ArrayList<Raid>(); 
		ObservableList<String> list = FXCollections.observableArrayList();
		raids.addAll(daoRaid.listerTout());
		for(Raid raid : raids)
		{
			list.add(raid.getNom_raid());
		}
		
		return list;
	}
	
}
