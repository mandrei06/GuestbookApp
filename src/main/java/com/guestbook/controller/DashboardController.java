package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.service.GuestbookEntryService;
import com.guestbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * DashboardController handles requests related to the user dashboard.
 * It manages displaying guestbook entries for the logged-in user and handling
 * new entry submissions including file uploads.
 */

@Controller
public class DashboardController {

    @Autowired
    private GuestbookEntryService guestbookEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("entries", guestbookEntryService.getAllEntriesByUser(username));
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String uploadEntry(@RequestParam("content") String content,
                              @RequestParam("image") MultipartFile image,
                              Model model) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        GuestbookEntry entry = new GuestbookEntry();
        entry.setUser(userService.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found")));
        entry.setTextContent(content);
        entry.setApproved(false);

        guestbookEntryService.saveEntry(entry, image);
        return "redirect:/dashboard";
    }
}
