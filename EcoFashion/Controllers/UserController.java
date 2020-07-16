package com.EFApplication.EcoFashion.Controllers;

import com.EFApplication.EcoFashion.Services.UserService;
import com.EFApplication.EcoFashion.Entities.User;

import com.EFApplication.EcoFashion.Wrappers.GetUserResponse;
import com.EFApplication.EcoFashion.Wrappers.LoginAndSignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<LoginAndSignUpResponse> signUpPOST(@Valid @RequestBody User user, BindingResult result) {
        Boolean errorstatus = result.hasErrors();

        if(errorstatus){
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        User exists = userService.findByuserName(user.userName);
        if (exists == null) {
            System.out.println("New user name is: " + user.getUserName());
            System.out.println("New user pw is: " + user.getUserPassword());
            System.out.println("New user email is: " + user.getUserEmail());
            userService.save(user);
        }
        else {
            List<String> errors = new ArrayList<>();
            errors.add("username already taken");
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, "Username already taken", null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new LoginAndSignUpResponse(user, "User created successfully", null), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getUsers")
    public List<User> usersGet(){
        return userService.findAll();
    }

    @RequestMapping(value = "/clearUsers")
    public String usersClear() {
        userService.deleteAll();
        return "Velkomin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginAndSignUpResponse> loginPOST(@RequestParam String username,
                                                            @RequestParam String userpassword,
                                                            HttpSession session) {
        User user = new User("", username, userpassword, "", "", "", "");

        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            System.out.println("Login virkadi!");
            return new ResponseEntity<>(new LoginAndSignUpResponse(exists, "Login success", null), HttpStatus.OK);
        }

        List<String> errors = new ArrayList<>();
        errors.add("Login unsuccessful");

        return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, errors), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponse> loggedinGET(HttpSession session) {

        System.out.println(session);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            System.out.println("User: " + sessionUser.getUserName() + " is logged in. loggedinGet");
            System.out.println("User id: " + sessionUser.getUserID() + " loggedinGet");

            return new ResponseEntity<>(new GetUserResponse(sessionUser), HttpStatus.OK);

        }
        List<String> errors = new ArrayList<>();
        errors.add("You must be logged in to visit this page");
        System.out.println("You must be logged in to visit this page");
        return new ResponseEntity<>(new GetUserResponse(null, null, errors), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value= "/saveUser", method = RequestMethod.POST)
    public void saveUser(@RequestParam String useremail,
                         @RequestParam String username,
                         @RequestParam String userpassword,
                         @RequestParam String userphone,
                         @RequestParam String useraddress,
                         @RequestParam String userzip,
                         @RequestParam String userstatus){
        User exists = userService.findByuserName(username.toString());
        if(exists != null) {
            System.out.println("Notandi nú þegar til");
        } else {
            userService.save(new User(useremail, username, userpassword, userphone, useraddress, userzip, userstatus));
            System.out.println("Notandi vistaður");
        }
    }
}
