package org.example.schoolmanagement.InDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressInDTO {

    @NotNull(message = "teacher ID is required")
    private Integer teacher_id;

    @NotBlank(message = "Area is required")
    @Size(min = 3, max = 20, message = "Area must be between 3 and 20 characters")
    private String area;

    @NotBlank(message = "Street is required")
    @Size(min = 3, max = 50, message = "Street must be between 3 and 50 characters")
    private String street;

    @NotBlank(message = "Building number is required")
    @Pattern(regexp = "\\d+", message = "Building number must be numeric")
    private String buildingNumber;
}
