package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.service.GuestbookEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GuestbookEntryControllerTest {

    @Mock
    private GuestbookEntryService guestbookEntryService;

    @Mock
    private Model model;

    @Mock
    private MultipartFile image;

    @InjectMocks
    private GuestbookEntryController guestbookEntryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEntries() {
        List<GuestbookEntry> entries = new ArrayList<>();
        when(guestbookEntryService.getAllEntries()).thenReturn(entries);

        String viewName = guestbookEntryController.getAllEntries(model);

        assertEquals("entries", viewName);
        verify(model, times(1)).addAttribute("entries", entries);
        verify(guestbookEntryService, times(1)).getAllEntries();
    }

    @Test
    void newEntryForm() {
        String viewName = guestbookEntryController.newEntryForm(model);

        assertEquals("entry-form", viewName);
        verify(model, times(1)).addAttribute(eq("entry"), any(GuestbookEntry.class));
    }

    @Test
    void createEntry() throws IOException {
        GuestbookEntry entry = new GuestbookEntry();

        String viewName = guestbookEntryController.createEntry(entry, image);

        assertEquals("redirect:/entries", viewName);
        verify(guestbookEntryService, times(1)).saveEntry(entry, image);
    }

    @Test
    void approveEntry() {
        Long id = 1L;

        String viewName = guestbookEntryController.approveEntry(id);

        assertEquals("redirect:/entries", viewName);
        verify(guestbookEntryService, times(1)).approveEntry(id);
    }

    @Test
    void deleteEntry() {
        Long id = 1L;

        String viewName = guestbookEntryController.deleteEntry(id);

        assertEquals("redirect:/entries", viewName);
        verify(guestbookEntryService, times(1)).deleteEntry(id);
    }
}
