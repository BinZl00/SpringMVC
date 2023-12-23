package com.bz.DTO;

import com.bz.PoJo.tUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String name;
    private Integer age;

    public tUser toTUser() {
        tUser user = new tUser();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

}