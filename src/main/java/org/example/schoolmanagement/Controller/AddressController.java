package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiResponse;
import org.example.schoolmanagement.InDTO.AddressInDTO;
import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Service.AddressService;
import org.example.schoolmanagement.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAddresses(Address address) {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
    @PostMapping("/add-address")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressInDTO addressInDTO) {
        addressService.AddAddress(addressInDTO);
        return ResponseEntity.ok(new ApiResponse("address added successfully"));
    }
    @PutMapping("/change")
    public ResponseEntity<?> changeAddress( @Valid @RequestBody AddressInDTO addressInDTO) {
        addressService.UpdateAddress(addressInDTO);
        return ResponseEntity.ok(new ApiResponse("address updated successfully"));
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer addressId) {
        addressService.DeleteAddress(addressId);
        return ResponseEntity.ok(new ApiResponse("address deleted successfully"));
    }
}
