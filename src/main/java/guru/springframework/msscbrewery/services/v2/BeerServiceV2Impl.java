package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.ALE)
                .build();
    }

    @Override
    public BeerDtoV2 createBeer(BeerDtoV2 beer) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void update(UUID id, BeerDtoV2 beer) {
        //implement here
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("received id: {}", id);
    }


}
