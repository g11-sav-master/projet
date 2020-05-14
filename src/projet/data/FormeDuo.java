package projet.data;

import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class FormeDuo {


	// Données observables
	
	protected final Property<Integer>	idUtilisateur	= new SimpleObjectProperty<>();
	protected final Property<Integer>	idPartDuo		= new SimpleObjectProperty<>();
	protected final Property<Boolean>	estCapitaine	= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public FormeDuo() {
	}

	public FormeDuo( int utilisateur, int idPartDuo, boolean estCapitaine) {
		setIdUtilisateur(idUtilisateur.getValue());
	}
	
	
	// Getters & setters

	
	public final Property<Integer> idUtilisateurProperty() {
		return this.idUtilisateur;
	}
	
	public final Integer getIdUtilisateur() {
		return this.idUtilisateurProperty().getValue();
	}
	
	public final void setIdUtilisateur(final Integer idUtilisateur) {
		this.idUtilisateurProperty().setValue(idUtilisateur);
	}
	
	public final Property<Integer> idPartDuoProperty() {
		return this.idPartDuo;
	}
	
	public final Integer getIdPartDuo() {
		return this.idPartDuoProperty().getValue();
	}
	
	public final void setIdPartDuo(Integer idPartDuo) {
		this.idPartDuoProperty().setValue(idPartDuo);
	}
	
	public final Property<Boolean> estCapitaineProperty() {
		return this.estCapitaine;
	}
	
	public final Boolean getEstCapitaine() {
		return this.estCapitaineProperty().getValue();
	}
	
	public final void setEstCapitaine(Boolean estCapitaine) {
		this.estCapitaineProperty().setValue(estCapitaine);
	}
	
	// toString()
	@Override
	public String toString() {
		return "";
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(idUtilisateur.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormeDuo other = (FormeDuo) obj;
		return Objects.equals(idUtilisateur.getValue(), other.idUtilisateur.getValue() );
	}
}
