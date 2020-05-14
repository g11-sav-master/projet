package projet.data;

import java.util.Objects;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class ParticipeOrganisation {


	// Données observables
	
	protected final Property<Integer>	idRaid	= new SimpleObjectProperty<>();
	protected final Property<Integer>	idUtilisateur	= new SimpleObjectProperty<>();
	protected final Property<Integer>	nbreRepas	= new SimpleObjectProperty<>();
	
	// Constructeurs
	
	public ParticipeOrganisation() {
	}

	public ParticipeOrganisation( int idRaid, int idUtilisateur, int nbreRepas ) {
		setIdUtilisateur(idUtilisateur);
		setIdRaid(idRaid);
		setNbreRepas(nbreRepas);
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
	
	public final Property<Integer> idRaidProperty() {
		return this.idRaid;
	}
	
	public final Integer getIdRaid() {
		return this.idRaidProperty().getValue();
	}
	
	public final void setIdRaid(final Integer idRaid) {
		this.idRaidProperty().setValue(idRaid);
	}
	
	public final Property<Integer> nbreRepasProperty() {
		return this.nbreRepas;
	}
	
	public final Integer getNbreRepas() {
		return this.nbreRepasProperty().getValue();
	}
	
	public final void setNbreRepas(final Integer nbreRepas) {
		this.nbreRepasProperty().setValue(nbreRepas);
	}
	
	// toString()
	@Override
	public String toString() {
		return getIdRaid()+" "+getIdUtilisateur()+" "+getNbreRepas();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(idRaid.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipeOrganisation other = (ParticipeOrganisation) obj;
		return Objects.equals(idRaid.getValue(), other.idRaid.getValue() );
	}
	
}
