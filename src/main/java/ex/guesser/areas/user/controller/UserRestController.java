package ex.guesser.areas.user.controller;

import com.google.gson.Gson;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.models.binding.UpdateUserPersonalBindingModel;
import ex.guesser.areas.user.models.dtos.UserDTO;
import ex.guesser.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    private final Gson gson;

    @Autowired
    public UserRestController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @GetMapping(value = "/myProfile", produces = "application/json")
    public @ResponseBody
    String edit(@ModelAttribute UpdateUserPersonalBindingModel bindingModel, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = this.userService.getById(user.getId());

        return this.gson.toJson(this.userService.getById(user.getId()));
    }
}
