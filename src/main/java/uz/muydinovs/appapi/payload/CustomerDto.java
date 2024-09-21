package uz.muydinovs.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotNull(message = "Full name shouldn't be empty")
    private String fullName;
    @NotNull(message = "Phone number shouldn't be empty")
    private String phoneNumber;
    @NotNull(message = "Address shouldn't be empty")
    private String address;
}
