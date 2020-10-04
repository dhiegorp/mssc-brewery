package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
    private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BeerDtoV2 beer) {
        BeerDtoV2 beerDto = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDto.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody BeerDtoV2 beer) {
        beerService.update(id, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        beerService.deleteById(id);
    }
}
