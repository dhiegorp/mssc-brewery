package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getById(UUID id) {
        return CustomerDto.builder()
                .id(id)
                .name("A Customer Name")
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("deleting customer by id: {}", id);
    }

    @Override
    public void update(UUID id, CustomerDto customer) {
        log.debug("updating customer with id: {} , values: {}", id, customer);
    }

    @Override
    public CustomerDto create(CustomerDto customer) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }
}
