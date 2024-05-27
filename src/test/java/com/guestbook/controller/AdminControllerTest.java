package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.service.GuestbookEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private GuestbookEntryService guestbookEntryService;

    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adminDashboard() {
        List<GuestbookEntry> entries = new ArrayList<>();
        when(guestbookEntryService.getAllEntries()).thenReturn(entries);

        String viewName = adminController.adminDashboard(model);

        assertEquals("admin-dashboard", viewName);
        verify(model, times(1)).addAttribute("entries", entries);
        verify(guestbookEntryService, times(1)).getAllEntries();
    }

    @Test
    void approveEntry() {
        Long id = 1L;

        String viewName = adminController.approveEntry(id);

        assertEquals("redirect:/admin/dashboard", viewName);
        verify(guestbookEntryService, times(1)).approveEntry(id);
    }

    @Test
    void deleteEntry() {
        Long id = 1L;

        String viewName = adminController.deleteEntry(id);

        assertEquals("redirect:/admin/dashboard", viewName);
        verify(guestbookEntryService, times(1)).deleteEntry(id);
    }
}
