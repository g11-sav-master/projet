package projet.data;

import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategorieRaid {


	// Données observables
	
	protected final Property<Integer>	idCategorieRaid	= new SimpleObjectProperty<>();
	protected final StringProperty		nomCategorie	= new SimpleStringProperty();
	
	// Constructeurs
	
	public CategorieRaid() {
	}

	public CategorieRaid( int id, String nom) {
		setIdCategorieRaid(id);
		setNomCategorie(nom);
	}
	
	
	// Getters & setters

	
	
	public final StringProperty nomCategorieProperty() {
		return this.nomCategorie;
	}
	
	public final String getNomCategorie() {
		return this.nomCategorieProperty().getValue();
	}
	
	public final void setNomCategorie(String nomCategorie) {
		this.nomCategorieProperty().setValue(nomCategorie);
	}
	
	public final Property<Integer> idCategorieRaidProperty() {
		return this.idCategorieRaid;
	}
	
	public final Integer getIdCategorieRaid() {
		return this.idCategorieRaidProperty().getValue();
	}
	
	public final void setIdCategorieRaid(Integer idCategorieRaid) {
		this.idCategorieRaidProperty().setValue(idCategorieRaid);
	}
	
	// toString()
	@Override
	public String toString() {
		return getIdCategorieRaid() + " " + getNomCategorie();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(idCategorieRaid.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategorieRaid other = (CategorieRaid) obj;
		return Objects.equals(idCategorieRaid.getValue(), other.idCategorieRaid.getValue() );
	}
	
}
