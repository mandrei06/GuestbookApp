package com.guestbook.service;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.repository.GuestbookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class GuestbookEntryService {

    // Service with logic for getAllEntries/ByUser, checking and saving the entry/image, approve/delete it
    @Value("${file.upload-dir}")
    String uploadDir;

    @Autowired
    private GuestbookEntryRepository guestbookEntryRepository;

    public List<GuestbookEntry> getAllEntries() {
        return guestbookEntryRepository.findAll();
    }

    public List<GuestbookEntry> getAllEntriesByUser(String username) {
        return guestbookEntryRepository.findAllByUserUsername(username);
    }

    public void saveEntry(GuestbookEntry entry, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            entry.setImageUrl(imagePath);
        }
        guestbookEntryRepository.save(entry);
    }

    private String saveImage(MultipartFile image) throws IOException {
        Path directoryPath = Paths.get(uploadDir);
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        String filename = image.getOriginalFilename();
        Path filepath = directoryPath.resolve(filename);
        Files.write(filepath, image.getBytes());
        return "/images/" + filename;
    }

    public void approveEntry(Long id) {
        GuestbookEntry entry = guestbookEntryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        entry.setApproved(true);
        guestbookEntryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        guestbookEntryRepository.deleteById(id);
    }
}