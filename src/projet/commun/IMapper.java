package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Participant;
import projet.data.Raid;
import projet.data.Poste;
import projet.data.CategorieRaid;


@Mapper
public interface IMapper {
	
	Benevole update(@MappingTarget Benevole benevole, Benevole source);
	Participant update(@MappingTarget Participant participant, Participant source);
	Raid		update(@MappingTarget Raid raid, Raid source);
	Poste update(@MappingTarget Poste poste, Poste source);
	CategorieRaid update (@MappingTarget CategorieRaid categorieraid, CategorieRaid source);
	
}
