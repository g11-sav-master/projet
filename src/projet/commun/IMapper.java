package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.ActionBenevole;
import projet.data.Benevole;
import projet.data.Participant;
import projet.data.ParticipantDuo;
import projet.data.ParticipeOrganisation;
import projet.data.Raid;
import projet.data.RoleBenevole;
import projet.data.ValidationMedicale;
import projet.data.Poste;
import projet.data.CategorieRaid;


@Mapper
public interface IMapper {
	
	Benevole update(@MappingTarget Benevole benevole, Benevole source);
	Participant update(@MappingTarget Participant participant, Participant source);
	Raid		update(@MappingTarget Raid raid, Raid source);
	Poste update(@MappingTarget Poste poste, Poste source);
	CategorieRaid update (@MappingTarget CategorieRaid categorieraid, CategorieRaid source);
	@Mapping( target="benevole", expression="java( source.getBenevole() )" )
	@Mapping( target="poste", expression="java( source.getPoste() )" )
	ActionBenevole update (@MappingTarget ActionBenevole actionBenevole, ActionBenevole source);
	ParticipeOrganisation update (@MappingTarget ParticipeOrganisation participeOrganisation,ParticipeOrganisation source);
	RoleBenevole update (@MappingTarget RoleBenevole actionBenevole, RoleBenevole source);
	@Mapping( target="participant", expression="java( source.getParticipant() )" )
	ValidationMedicale update (@MappingTarget ValidationMedicale validationMedicale, ValidationMedicale source);
	ParticipantDuo update(@MappingTarget ParticipantDuo courant, ParticipantDuo retrouver);
	
}
