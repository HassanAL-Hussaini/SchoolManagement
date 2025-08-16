package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiException;
import org.example.schoolmanagement.InDTO.AddressInDTO;
import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.AddressRepo;
import org.example.schoolmanagement.Repository.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo addressRepo;
    private final TeacherRepo teacherRepo;

    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    public void AddAddress(AddressInDTO addressInDTO) {
        Teacher  teacher = teacherRepo.findTeacherById(addressInDTO.getTeacher_id());
        if(teacher==null){
            throw new ApiException("Teacher not found");
        }
        //addressInDTO.getTeacher_id()
        Address address = new Address(null, addressInDTO.getArea(), addressInDTO.getBuildingNumber(), addressInDTO.getStreet(), teacher);
        addressRepo.save(address);
    }

    public void UpdateAddress(AddressInDTO addressInDTO) {
        Address oldAddress = addressRepo.findAddressById(addressInDTO.getTeacher_id());
        if(oldAddress==null){
            throw new ApiException("Address not found");
        }
        oldAddress.setArea(addressInDTO.getArea());
        oldAddress.setBuildingNumber(addressInDTO.getBuildingNumber());
        oldAddress.setStreet(addressInDTO.getStreet());
        addressRepo.save(oldAddress);
    }

    //this will never work because it's one-to-one relation with the Teacher and the address is the follower, and we can't delete the follower while the boss is existed
    public void DeleteAddress(Integer addressId) {
        Address oldAddress = addressRepo.findAddressById(addressId);
        if(oldAddress==null){
            throw new ApiException("address not found");
        }
        addressRepo.deleteById(addressId);
    }



}
