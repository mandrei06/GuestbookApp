package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.service.GuestbookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/entries")
public class GuestbookEntryController {
    // Class responsible with actions for entries, adding new entry, as well for remove/approve it

    @Autowired
    private GuestbookEntryService guestbookEntryService;

    @GetMapping
    public String getAllEntries(Model model) {
        List<GuestbookEntry> entries = guestbookEntryService.getAllEntries();
        model.addAttribute("entries", entries);
        return "entries";
    }

    @GetMapping("/new")
    public String newEntryForm(Model model) {
        model.addAttribute("entry", new GuestbookEntry());
        return "entry-form";
    }

    @PostMapping
    public String createEntry(@ModelAttribute GuestbookEntry entry,
                              @RequestParam("image") MultipartFile image) throws IOException {
        guestbookEntryService.saveEntry(entry, image);
        return "redirect:/entries";
    }

    @PostMapping("/approve/{id}")
    public String approveEntry(@PathVariable Long id) {
        guestbookEntryService.approveEntry(id);
        return "redirect:/entries";
    }

    @PostMapping("/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {
        guestbookEntryService.deleteEntry(id);
        return "redirect:/entries";
    }
}
