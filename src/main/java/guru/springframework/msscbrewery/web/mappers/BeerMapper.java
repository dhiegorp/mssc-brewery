package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.mapstruct.Mapper;

@Mapper(uses={DateMapper.class})
public interface BeerMapper {
    BeerDtoV2 beerToBeerDTOV2(Beer beer);
    Beer beerDTOV2ToBeer(BeerDtoV2 beer);
    BeerDto beerToBeerDTO(Beer beer);
    Beer beerToBeerDTO(BeerDto beer);
}
