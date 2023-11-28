package com.code.orishop.service;

import com.code.orishop.model.entity.RoleEntity;
import com.code.orishop.model.entity.UserEntity;
import com.code.orishop.model.request.UserReq;
import com.code.orishop.repository.RoleRepository;
import com.code.orishop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public UserEntity getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->{
                    try {
                        throw new Exception("Not found");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public UserEntity createUser(UserReq userReq) throws Exception {
        UserEntity user = userRepository.findByUserName(userReq.getUserName());
        if(user != null) throw new Exception("User already exists!");

        Optional<UserEntity> user2 = userRepository.findByEmail(userReq.getEmail());
        if(user2.isPresent()) throw new Exception("User already exists!");

        RoleEntity role = roleRepository.findById(userReq.getRole())
                .orElse(null);

        UserEntity userEntity = UserEntity.builder()
                .userName(userReq.getUserName())
                .email(userReq.getEmail())
                .phone(userReq.getPhone())
                .roles(List.of(role))
                .password(userReq.getPassword())
                .build();
        userRepository.save(userEntity);
        return userEntity;
    }

    public void updateUser(UserReq userReq, Long id) throws Exception {
        UserEntity user = userRepository.findByUserName(userReq.getUserName());
        if(user != null && !Objects.equals(user.getId(), id)) throw new Exception("User already exists!");

        Optional<UserEntity> user2 = userRepository.findByEmail(userReq.getEmail());
        if(user2.isPresent() && !Objects.equals(user2.get().getId(), id)) throw new Exception("User already exists!");

        RoleEntity role = roleRepository.findById(userReq.getRole())
                .orElse(null);
        UserEntity userEntity = getById(id);
        userEntity.setUserName(userReq.getUserName());
        userEntity.setPassword(userReq.getPassword());
        userEntity.setPhone(userReq.getPhone());
        userEntity.setEmail(userReq.getEmail());
        userEntity.getRoles().clear();
        userEntity.getRoles().add(role);
        userRepository.save(userEntity);
    }
    public String getRole(String userName) {
        UserEntity user = userRepository.findByUserName(userName);
        if(user !=null) return user.getRoles().get(0).getRoleName();
        return null;
    }
    public UserEntity login(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty() || !user.get().getPassword().equals(password)) {
            return null;
        }
        return user.get();
    }
    public List<UserEntity> searchCustomer (String userName ){
        return userRepository.findAllCustomersByUserName(userName);
    }
}
