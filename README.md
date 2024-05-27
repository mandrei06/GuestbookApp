## Video Demo


https://github.com/mandrei06/GuestbookApp/assets/54832870/251ac8c3-966a-471f-91e9-a6ee39b8e5be



# Application Task - Guestbook

## Requirements

Create a guestbook application which consists of two types of users:

**Guests**  
**Administrator**

The tool should be developed with OOP/MVC approach. The structure of the database tables should fit the model/entity classes.

Create a public GitHub or Bitbucket repository and share the URL with us once completed.

## Technologies to Use
(Please use the most recent supported versions available):

- **Backend Framework**: Java/Spring
- **Frontend Framework**: Thymeleaf/Bootstrap/React
- **Database**: Any DB (MySQL, MongoDB, etc.)
- **JUnit**
- **Git**

## Guests

- Users need to log in to write a new entry in the guestbook.
- Guestbook entry can be either a single image or text.

## Administrator

- View all the entries posted by all the users.
- Approve the entries.
- Remove the entries.

## JUnit Tests

- Unit tests to test the services/functions.

## Summary

- Write a guestbook application.
- A guestbook entry can be an image or text.
- Guest users need to log in to write an entry.
- Administrators can view, edit, approve, or delete entries.
- Share the GitHub or Bitbucket URL.


# Guestbook Application

This is a simple guestbook application built using Java and Spring framework with a Thymeleaf frontend. The application allows guests to register, login, and post entries in the guestbook. Administrators can view, approve, and delete these entries.

## Features

### Guests
- **Login:** Guests must login to write a new entry.
- **Post Entries:** Guests can post entries which can either be text or an image.

### Administrator
- **View Entries:** Admin can view all entries posted by users.
- **Approve Entries:** Admin can approve guestbook entries.
- **Remove Entries:** Admin can delete guestbook entries.

## Technologies Used

- **Backend Framework:** Java/Spring
- **Frontend Framework:** Thymeleaf/Bootstrap
- **Database:** MySQL
- **Testing:** JUnit
- **Version Control:** Git

## Security
- **BCryptPasswordEncoder**: This application utilizes `BCryptPasswordEncoder` for hashing user passwords with a salt. This method provides a very strong/secure way of storing passwords compared to other methods for hashes.
- **CSRF Protection**: The application is secured against CSRF (Cross-Site Request Forgery) attacks by integrating CSRF tokens in forms where sensitive information is submitted.

