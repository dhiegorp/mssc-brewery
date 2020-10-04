package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface CustomerService {
    public CustomerDto getById(UUID id);

    void deleteById(UUID id);

    void update(UUID id, CustomerDto customer);

    CustomerDto create(CustomerDto customer);
}
