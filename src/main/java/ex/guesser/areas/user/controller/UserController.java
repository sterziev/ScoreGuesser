package ex.guesser.areas.user.controller;

import ex.guesser.areas.common.controller.BaseController;
import ex.guesser.areas.errorHandling.errors.UserNotFound;
import ex.guesser.areas.errorHandling.errors.WrongPassword;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.models.binding.RegisterBindingModel;
import ex.guesser.areas.user.models.binding.UpdatePasswordBM;
import ex.guesser.areas.user.models.binding.UpdateUserPersonalBindingModel;
import ex.guesser.areas.user.models.dtos.UserDTO;
import ex.guesser.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false, name = "error") String error, ModelAndView modelAndView, Authentication authentication) {
        userService.isLoggedIn(authentication);
        if (error != null){
            modelAndView.addObject("error", error);
            return this.view("user/login","error",error);
        }
        return this.view("user/login");
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN','MODERATOR')")
    public ModelAndView logout(@RequestParam(required = false, name = "logout") String logout, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("redirect:/login?logout");
        if (logout != null) redirectAttributes.addFlashAttribute("logout", logout);
        return modelAndView;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute RegisterBindingModel bindingModel) {
        return this.view("user/register","user",bindingModel);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("user") RegisterBindingModel bindingModel,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()){
                bindingResult.rejectValue("password",null,bindingResult.getGlobalError().getDefaultMessage());
            }
            return this.view("user/register");
        }
        this.userService.register(bindingModel);
        return this.redirect("/login");
    }

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized() {
        return this.view("user/unauthorized");
    }

    @GetMapping("/user/show")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView show() {

        return this.view("user/listUsers","users", this.userService.getAll());

    }

    @GetMapping("/user/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable String id, ModelAndView modelAndView) {
        UserDTO userDTO = this.userService.getById(id);
        if (userDTO == null) {
            modelAndView.setViewName("redirect:/user/show");
            return modelAndView;
        }

        modelAndView.addObject("id", id);
        modelAndView.addObject("user", userDTO);
        modelAndView.setViewName("user/delete");
        return modelAndView;
    }

    @PostMapping("/user/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView deletePost(@PathVariable String id, ModelAndView modelAndView) {
        this.userService.deleteById(id);
        modelAndView.setViewName("redirect:/user/show");
        return modelAndView;
    }

    @GetMapping("/user/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable String id, Model model) {
        if (!model.containsAttribute("user")) {
            UserDTO userDTO = this.userService.getById(id);
            if (userDTO == null) {
                throw new UserNotFound();
            }

            model.addAttribute("user", userDTO);
        }
        model.addAttribute("id", id);

        return "user/edit";
    }

    @PostMapping("user/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable String id, @ModelAttribute UserDTO bindingModel, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("user", bindingModel);

            return "redirect:/user/edit/" + id;
        }

        this.userService.updateUserRoles(id, bindingModel);

        return "redirect:/user/show";
    }


    @GetMapping("/user/myProfile")
    public ModelAndView edit(@ModelAttribute UpdateUserPersonalBindingModel bindingModel, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = this.userService.getById(user.getId());
        return this.view("user/myProfile/show","user",userDTO);
    }

    @GetMapping("/user/editPersonalInfo")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editInfo(@ModelAttribute UpdateUserPersonalBindingModel bindingModel, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDTO userDTO = this.userService.getById(user.getId());
        return this.view("user/myProfile/editPersonalInfo","user",userDTO);
    }

    @PostMapping("user/editPersonalInfo")
    public ModelAndView edit(@Valid @ModelAttribute("user") UpdateUserPersonalBindingModel bindingModel,
                             BindingResult bindingResult,
                             Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return this.view("user/myProfile/editPersonalInfo","user",bindingModel);
        }
        User user = (User) authentication.getPrincipal();
        this.userService.updateUserPersonalInfo(user.getId(), bindingModel);
        return this.redirect("/user/myProfile");
    }


    @GetMapping("/user/editPassword")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPass(@ModelAttribute UpdateUserPersonalBindingModel bindingModel) {
        return this.view("user/myProfile/editPassword","passwordDTO",new UpdatePasswordBM());
    }

    @PostMapping("user/editPassword")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPassword(@Valid @ModelAttribute("passwordDTO") UpdatePasswordBM bindingModel,
                                     BindingResult bindingResult,Authentication authentication) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()){
                bindingResult.rejectValue("password",null,bindingResult.getGlobalError().getDefaultMessage());
            }
            return this.view("user/myProfile/editPassword","passwordDTO",bindingModel);
        }
        User user = (User) authentication.getPrincipal();

        try {
            this.userService.editPassword(user.getId(), bindingModel);
        }
        catch (WrongPassword wp){
            bindingResult.rejectValue("oldPassword",null,"Wrong Password");
            return this.view("user/myProfile/editPassword","passwordDTO",bindingModel);
        }
        return this.redirect("/user/myProfile");
    }
}
