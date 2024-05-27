package com.guestbook.controller;

import com.guestbook.entity.User;
import com.guestbook.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerForm() {
        String viewName = userController.registerForm(model);
        assertEquals("register", viewName);
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void registerUser() {
        User user = new User();
        Model model = mock(Model.class);

        String viewName = userController.registerUser(user, model);
        assertEquals("redirect:/login", viewName);
        verify(userService, times(1)).saveUser(user);
    }
}
