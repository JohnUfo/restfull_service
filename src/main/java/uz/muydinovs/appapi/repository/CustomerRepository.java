package uz.muydinovs.appapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.muydinovs.appapi.entity.Customer;

import javax.validation.constraints.NotNull;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhoneNumber(@NotNull String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(@NotNull String phoneNumber, Integer id);
}
