package com.code.orishop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "`user`")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity{
    @Column(columnDefinition = "nvarchar(MAX)")
    private String userName;
    @Column
    private String password;
    @Column
    private String email;

    @Column
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "`user_role`",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<OrderEntity> orders;

}
