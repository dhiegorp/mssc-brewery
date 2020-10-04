package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CustomerDto customer) {
        CustomerDto newCustomer = service.create(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + newCustomer.getId().toString() );
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody CustomerDto customer) {
        service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}
