package com.guestbook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        String viewName = homeController.home();
        assertEquals("index", viewName);
    }

    @Test
    void login() {
        String viewName = homeController.login();
        assertEquals("login", viewName);
    }
}
