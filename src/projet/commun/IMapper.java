package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Participant;


@Mapper
public interface IMapper {
	
	Benevole update(@MappingTarget Benevole benevole, Benevole source);
	Participant update(@MappingTarget Participant participant, Participant source);
	
}
