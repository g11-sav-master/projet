package projet.data;

import java.util.Objects;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class RoleBenevole {

	// Données observables
	private final Property<Integer> id_role = new SimpleObjectProperty<>();
	private final Property<String> nom_role = new SimpleObjectProperty<>();

	// Constructeurs

	public RoleBenevole() {};

	public RoleBenevole(int id_role, String nom_role) {
		setId_role(id_role);
		setNom_role(nom_role);
	}

	// Getters & setters

	public final Property<Integer> id_roleProperty() {
		return this.id_role;
	}

	public final Integer getId_role() {
		return this.id_roleProperty().getValue();
	}

	public final void setId_role(final Integer id_role) {
		this.id_roleProperty().setValue(id_role);
	}

	public final Property<String> nom_roleProperty() {
		return this.nom_role;
	}

	public final String getNom_role() {
		return this.nom_roleProperty().getValue();
	}

	public final void setNom_role(final String nom_role) {
		this.nom_roleProperty().setValue(nom_role);
	}

	//Hashcode & Equals & toString
	
	@Override
	public int hashCode() {
		return Objects.hash(id_role, nom_role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleBenevole other = (RoleBenevole) obj;
		return Objects.equals(id_role, other.id_role) && Objects.equals(nom_role, other.nom_role);
	}
	
	@Override
	public String toString() {
		return getId_role() + " " + getNom_role();
	}

}
