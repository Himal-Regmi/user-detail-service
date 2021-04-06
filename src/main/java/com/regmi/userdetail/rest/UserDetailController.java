package com.regmi.userdetail.rest;

import com.regmi.userdetail.exception.BadRequestException;
import com.regmi.userdetail.exception.UnauthorizedException;
import com.regmi.userdetail.exception.UserDetailNotFoundException;
import com.regmi.userdetail.model.UserDetailEntity;
import com.regmi.userdetail.rest.payload.request.UserDetails;
import com.regmi.userdetail.security.CurrentUser;
import com.regmi.userdetail.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user-details")
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping
    public UserDetailEntity getUserDetail(){
        CurrentUser currentUser=(CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailService.getUserDetailsByUserId(currentUser.getId());
    }
    @GetMapping("/{id}")
    public UserDetailEntity getUserDetailById(@PathVariable int id){
        return userDetailService.getUserDetails(id);
    }
    @GetMapping("user/{id}")
    public UserDetailEntity getUserDetailByUserId(@PathVariable int id){
        return userDetailService.getUserDetailsByUserId(id);
    }
    @PostMapping
    public ResponseEntity<Object> addUserDetailToCurrentUser(@Valid @RequestBody UserDetails userDetails) {
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailEntity user=
                new UserDetailEntity(userDetails.getFullName(),userDetails.getEmail(),userDetails.getNumber());
        if(userDetailService.getUserDetailsByUserId(currentuser.getId())!=null){
            throw new BadRequestException("User Details already exits");
        }
        user.setUserId(currentuser.getId());
        userDetailService.saveUserDetails(user);
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDetailEntity> patchUserDetail(@Valid @RequestBody UserDetails userDetails,
                                                            @PathVariable int id){
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailEntity userDetail=userDetailService.getUserDetails(id);
        if(currentuser.getId()!=userDetail.getUserId()){
            throw new UnauthorizedException("Not Authorized for id-"+id);
        }else{
            UserDetailEntity newUserDetail=
                    new UserDetailEntity(userDetails.getFullName(),userDetails.getEmail(),userDetails.getNumber());

            newUserDetail.setId(userDetail.getId());
            newUserDetail.setUserId(currentuser.getId());
            if(newUserDetail.getName()!=null){
                userDetail.setName(newUserDetail.getName());
            }
            if(newUserDetail.getEmail()!=null){
                userDetail.setEmail(newUserDetail.getEmail());
            }
            if(newUserDetail.getContactNumber()!=0){
                userDetail.setContactNumber(newUserDetail.getContactNumber());
            }
            userDetailService.updateUserDetails(userDetail);
            return ResponseEntity.ok(userDetail);
        }

    }
    @DeleteMapping("/{id}")
    public void deleteUserDetails(@PathVariable int id){
        CurrentUser currentuser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailEntity userDetail=userDetailService.getUserDetails(id);
        if(currentuser.getId()!=userDetail.getUserId()){
            throw new UnauthorizedException("Not Authorized for id-"+id);
        }
        if(userDetailService.getUserDetails(id) == null){
            throw new UserDetailNotFoundException("No details available to delete");
        }
        userDetailService.deleteUserDetailsById(id);
    }

}
