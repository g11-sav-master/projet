package projet.commun;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;


@Mapper
public interface IMapper {
	
	Benevole update(@MappingTarget Benevole benevole, Benevole source);
	
}
