package com.Ecommers.shopping.DtoRequest;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CustomerRequestDto {

    private String name;

    private int age;

    private String mobileNo;

    private String email;

}
