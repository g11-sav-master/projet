package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class ParticipantDuo {

	// Données observables
	private final Property<Integer>		id_part_duo		= new SimpleObjectProperty<>();
	private final Property<Integer>		id_categorie	= new SimpleObjectProperty<>();
	private final Property<Integer>		id_raid			= new SimpleObjectProperty<>();
	private final Property<Integer>		nbr_repas		= new SimpleObjectProperty<>();
	private final Property<Boolean>		paiement_valide	= new SimpleObjectProperty<>();

	// Constructeurs

	public ParticipantDuo() {
	}

	public ParticipantDuo(int id_partduo, int id_cat, int id_raid, int nbrrepas, boolean paiement) {
		setId_part_duo(id_partduo);
		setId_categorie(id_cat);
		setId_raid(id_raid);
		setNbr_repas(nbrrepas);
		setPaiement_valide(paiement);
	}
	
	// Getters & setters

	public final Property<Integer> id_part_duoProperty() {
		return this.id_part_duo;
	}
	

	public final Integer getId_part_duo() {
		return this.id_part_duoProperty().getValue();
	}
	

	public final void setId_part_duo(final Integer id_part_duo) {
		this.id_part_duoProperty().setValue(id_part_duo);
	}
	

	public final Property<Integer> id_categorieProperty() {
		return this.id_categorie;
	}
	

	public final Integer getId_categorie() {
		return this.id_categorieProperty().getValue();
	}
	

	public final void setId_categorie(final Integer id_categorie) {
		this.id_categorieProperty().setValue(id_categorie);
	}
	

	public final Property<Integer> id_raidProperty() {
		return this.id_raid;
	}
	

	public final Integer getId_raid() {
		return this.id_raidProperty().getValue();
	}
	

	public final void setId_raid(final Integer id_raid) {
		this.id_raidProperty().setValue(id_raid);
	}
	

	public final Property<Boolean> paiement_valideProperty() {
		return this.paiement_valide;
	}
	

	public final Boolean getPaiement_valide() {
		return this.paiement_valideProperty().getValue();
	}
	

	public final void setPaiement_valide(final Boolean paiement_valide) {
		this.paiement_valideProperty().setValue(paiement_valide);
	}

	public final Property<Integer> nbr_repasProperty() {
		return this.nbr_repas;
	}
	

	public final Integer getNbr_repas() {
		return this.nbr_repasProperty().getValue();
	}
	

	public final void setNbr_repas(final Integer nbr_repas) {
		this.nbr_repasProperty().setValue(nbr_repas);
	}
	
	//Hashcode & Equals & toString
	
	@Override
	public int hashCode() {
		return Objects.hash(id_categorie, id_part_duo, id_raid, paiement_valide);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipantDuo other = (ParticipantDuo) obj;
		return Objects.equals(id_categorie, other.id_categorie) && Objects.equals(id_part_duo, other.id_part_duo)
				&& Objects.equals(id_raid, other.id_raid) && Objects.equals(paiement_valide, other.paiement_valide);
	}

	@Override
	public String toString() {
		return getId_part_duo() + " " + getPaiement_valide();
	}
}
