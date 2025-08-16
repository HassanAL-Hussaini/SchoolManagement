package org.example.schoolmanagement.Repository;

import org.example.schoolmanagement.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    Address findAddressById(Integer customer_id);
}
