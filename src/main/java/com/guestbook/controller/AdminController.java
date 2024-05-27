package com.guestbook.controller;

import com.guestbook.entity.GuestbookEntry;
import com.guestbook.service.GuestbookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //class responsible with admin functionalities: see all entries to approve or delete them

    @Autowired
    private GuestbookEntryService guestbookEntryService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("entries", guestbookEntryService.getAllEntries());
        return "admin-dashboard";
    }

    @PostMapping("/approve/{id}")
    public String approveEntry(@PathVariable Long id) {
        guestbookEntryService.approveEntry(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {
        guestbookEntryService.deleteEntry(id);
        return "redirect:/admin/dashboard";
    }
}
