package com.guestbook.service;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.repository.GuestbookEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GuestbookEntryServiceTest {

    @Mock
    private GuestbookEntryRepository guestbookEntryRepository;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private GuestbookEntryService guestbookEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        guestbookEntryService.uploadDir = "uploads/";
    }

    @Test
    void getAllEntries() {
        GuestbookEntry entry1 = new GuestbookEntry();
        GuestbookEntry entry2 = new GuestbookEntry();
        List<GuestbookEntry> entries = Arrays.asList(entry1, entry2);

        when(guestbookEntryRepository.findAll()).thenReturn(entries);

        List<GuestbookEntry> result = guestbookEntryService.getAllEntries();
        assertEquals(2, result.size());
        verify(guestbookEntryRepository, times(1)).findAll();
    }

    @Test
    void getAllEntriesByUser() {
        String username = "user";
        GuestbookEntry entry1 = new GuestbookEntry();
        GuestbookEntry entry2 = new GuestbookEntry();
        List<GuestbookEntry> entries = Arrays.asList(entry1, entry2);

        when(guestbookEntryRepository.findAllByUserUsername(username)).thenReturn(entries);

        List<GuestbookEntry> result = guestbookEntryService.getAllEntriesByUser(username);
        assertEquals(2, result.size());
        verify(guestbookEntryRepository, times(1)).findAllByUserUsername(username);
    }

    @Test
    void saveEntryWithoutImage() throws IOException {
        GuestbookEntry entry = new GuestbookEntry();
        guestbookEntryService.saveEntry(entry, null);

        verify(guestbookEntryRepository, times(1)).save(entry);
    }

    @Test
    void saveEntryWithImage() throws IOException {
        GuestbookEntry entry = new GuestbookEntry();
        String filename = "test.png";
        byte[] content = "test content".getBytes();
        Path path = Paths.get("uploads/", filename);

        when(multipartFile.getOriginalFilename()).thenReturn(filename);
        when(multipartFile.getBytes()).thenReturn(content);

        guestbookEntryService.saveEntry(entry, multipartFile);

        assertEquals("/images/" + filename, entry.getImageUrl());
        verify(guestbookEntryRepository, times(1)).save(entry);
        assertTrue(Files.exists(path));

        // Clean up the created file after test
        Files.deleteIfExists(path);
    }

    @Test
    void approveEntry() {
        Long id = 1L;
        GuestbookEntry entry = new GuestbookEntry();
        entry.setId(id);

        when(guestbookEntryRepository.findById(id)).thenReturn(Optional.of(entry));

        guestbookEntryService.approveEntry(id);

        assertTrue(entry.isApproved());
        verify(guestbookEntryRepository, times(1)).save(entry);
    }

    @Test
    void approveEntryInvalidId() {
        Long id = 1L;

        when(guestbookEntryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> guestbookEntryService.approveEntry(id));
    }

    @Test
    void deleteEntry() {
        Long id = 1L;

        guestbookEntryService.deleteEntry(id);

        verify(guestbookEntryRepository, times(1)).deleteById(id);
    }
}
