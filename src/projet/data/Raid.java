package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Raid {

	private final Property<Integer>	id		= new SimpleObjectProperty<>();
	private	final StringProperty nom_raid 	= new SimpleStringProperty();
	private final Property<LocalDate> annee = new SimpleObjectProperty<>();
	private final Property<Double> prix_insc = new SimpleObjectProperty<>();
	private final Property<Double> prix_repas = new SimpleObjectProperty<>();
	
	//constructeur
	public Raid() {};
	public Raid(final int id, final String nom, final LocalDate annee, final double prix_i, final double prix_r) 
	{
		setId(id);
		setNom_raid(nom);
		setAnnee(annee);
		setPrix_insc(prix_i);
		setPrix_repas(prix_r);
	}
	
	//getters and setters
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nomProperty() {
		return this.nom_raid;
	}
	
	public final String getNom_raid() {
		return this.nomProperty().get();
	}
	
	public final void setNom_raid(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final Property<LocalDate> anneeProperty() {
		return this.annee;
	}
	
	public final LocalDate getAnnee() {
		return this.anneeProperty().getValue();
	}
	
	public final void setAnnee(final LocalDate annee) {
		this.anneeProperty().setValue(annee);
	}
	
	public final Property<Double> prix_inscProperty() {
		return this.prix_insc;
	}
	
	public final Double getPrix_insc() {
		return this.prix_inscProperty().getValue();
	}
	
	public final void setPrix_insc(final Double prix_insc) {
		this.prix_inscProperty().setValue(prix_insc);
	}
	
	public final Property<Double> prix_repasProperty() {
		return this.prix_repas;
	}
	
	public final Double getPrix_repas() {
		return this.prix_repasProperty().getValue();
	}
	
	public final void setPrix_repas(final Double prix_repas) {
		this.prix_repasProperty().setValue(prix_repas);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNom_raid()+" "+this.getAnnee();
	}
	
}
