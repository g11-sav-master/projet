package projet.data;

import java.time.LocalTime;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ActionBenevole {
	private final Property<Integer> id_action = new SimpleObjectProperty<>();
	private final Property<Poste> poste = new SimpleObjectProperty<>();
	private final Property<Benevole> benevole = new SimpleObjectProperty<>();
	private final Property<Boolean> panneau_prendre = new SimpleObjectProperty<>();
	private final Property<String>  descr_action = new SimpleStringProperty();
	private final Property<Boolean> signaleur = new SimpleObjectProperty<>();
	private final Property<LocalTime> horaire_debut = new SimpleObjectProperty<>();
	private final Property<LocalTime> horaire_fin = new SimpleObjectProperty<>();
	
	//constructer
	public ActionBenevole() {};
	public ActionBenevole( int id_action,Poste poste, Benevole benevole, boolean panneau_prendre, String descr_action, boolean signaleur, LocalTime horaire_debut, LocalTime horaire_fin) {
		setId_action(id_action);
		setPoste(poste);
		setBenevole(benevole);
		setPanneau_prendre(panneau_prendre);
		setDescr_action(descr_action);
		setSignaleur(signaleur);
		setHoraire_debut(horaire_debut);
		setHoraire_fin(horaire_fin);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "action :"+this.getId_action()+" - "+this.getHoraire_debut()+" - "+this.getHoraire_fin();
	}
	
	//getter & setter
	
	public final Property<Integer> id_actionProperty() {
		return this.id_action;
	}
	
	public final Integer getId_action() {
		return this.id_actionProperty().getValue();
	}
	
	public final void setId_action(final Integer id_action) {
		this.id_actionProperty().setValue(id_action);
	}
	
	public final Property<Poste> posteProperty() {
		return this.poste;
	}
	
	public final Poste getPoste() {
		return this.posteProperty().getValue();
	}
	
	public final void setPoste(final Poste poste) {
		this.posteProperty().setValue(poste);
	}
	
	public final Property<Benevole> benevoleProperty() {
		return this.benevole;
	}
	
	public final Benevole getBenevole() {
		return this.benevoleProperty().getValue();
	}
	
	public final void setBenevole(final Benevole benevole) {
		this.benevoleProperty().setValue(benevole);
	}
	
	public final Property<Boolean> panneau_prendreProperty() {
		return this.panneau_prendre;
	}
	
	public final Boolean getPanneau_prendre() {
		return this.panneau_prendreProperty().getValue();
	}
	
	public final void setPanneau_prendre(final Boolean panneau_prendre) {
		this.panneau_prendreProperty().setValue(panneau_prendre);
	}
	
	public final Property<String> descr_actionProperty() {
		return this.descr_action;
	}
	
	public final String getDescr_action() {
		return this.descr_actionProperty().getValue();
	}
	
	public final void setDescr_action(final String descr_action) {
		this.descr_actionProperty().setValue(descr_action);
	}
	
	public final Property<Boolean> signaleurProperty() {
		return this.signaleur;
	}
	
	public final Boolean getSignaleur() {
		return this.signaleurProperty().getValue();
	}
	
	public final void setSignaleur(final Boolean signaleur) {
		this.signaleurProperty().setValue(signaleur);
	}
	
	public final Property<LocalTime> horaire_debutProperty() {
		return this.horaire_debut;
	}
	
	public final LocalTime getHoraire_debut() {
		return this.horaire_debutProperty().getValue();
	}
	
	public final void setHoraire_debut(final LocalTime horaire_debut) {
		this.horaire_debutProperty().setValue(horaire_debut);
	}
	
	public final Property<LocalTime> horaire_finProperty() {
		return this.horaire_fin;
	}
	
	public final LocalTime getHoraire_fin() {
		return this.horaire_finProperty().getValue();
	}
	
	public final void setHoraire_fin(final LocalTime horaire_fin) {
		this.horaire_finProperty().setValue(horaire_fin);
	}
	

	
	
	

}
