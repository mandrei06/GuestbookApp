package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.entity.User;
import com.guestbook.service.GuestbookEntryService;
import com.guestbook.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private GuestbookEntryService guestbookEntryService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private MultipartFile image;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void dashboard() {
        String username = "testuser";
        List<GuestbookEntry> entries = new ArrayList<>();

        when(authentication.getName()).thenReturn(username);
        when(guestbookEntryService.getAllEntriesByUser(username)).thenReturn(entries);

        String viewName = dashboardController.dashboard(model);

        assertEquals("dashboard", viewName);
        verify(model, times(1)).addAttribute("entries", entries);
        verify(guestbookEntryService, times(1)).getAllEntriesByUser(username);
    }

    @Test
    void uploadEntry() throws IOException {
        String username = "testuser";
        String content = "test content";
        User user = new User();
        GuestbookEntry entry = new GuestbookEntry();

        when(authentication.getName()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(Optional.of(user));

        String viewName = dashboardController.uploadEntry(content, image, model);

        assertEquals("redirect:/dashboard", viewName);
        verify(guestbookEntryService, times(1)).saveEntry(any(GuestbookEntry.class), eq(image));
    }

    @Test
    void uploadEntryUserNotFound() {
        String username = "testuser";
        String content = "test content";

        when(authentication.getName()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> dashboardController.uploadEntry(content, image, model));
    }
}
