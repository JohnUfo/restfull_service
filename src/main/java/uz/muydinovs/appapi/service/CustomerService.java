package uz.muydinovs.appapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muydinovs.appapi.payload.CustomerDto;
import uz.muydinovs.appapi.entity.Customer;
import uz.muydinovs.appapi.payload.ApiResponse;
import uz.muydinovs.appapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }


    public ApiResponse create(CustomerDto customerDto) {
        boolean exists = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (exists) {
            return new ApiResponse("This user is already exist", false);
        }
        Customer customer = new Customer();
        customer.setFullName(customerDto.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        return new ApiResponse("Customer created", true);
    }

    public ApiResponse update(Integer id, CustomerDto customerDto) {
        boolean exists = customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id);
        if (exists) {
            return new ApiResponse("This user is already exist", false);
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            return new ApiResponse("Customer not found", false);
        }

        Customer customer = optionalCustomer.get();
        customer.setFullName(customerDto.getFullName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        return new ApiResponse("Customer updated", true);
    }

    public ApiResponse delete(Integer id) {
        try {
            customerRepository.deleteById(id);
            return new ApiResponse("Customer deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Customer not found", false);
        }
    }
}
