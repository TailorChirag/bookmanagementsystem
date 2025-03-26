package example.com.bookmanagementsystem.controller;


import example.com.bookmanagementsystem.exception.DuplicateBookException;
import example.com.bookmanagementsystem.models.Book;
import example.com.bookmanagementsystem.repository.BookRepository;
import example.com.bookmanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    @Autowired
    private  BookRepository bookRepository;



    // Add Book a new book with validation
    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
        if (bookRepository.existsByTitle(book.getTitle())) {
            throw new DuplicateBookException("Book with this title already exists!");
        }

        // Ensure only valid status is accepted
        if (book.getAvailabilityStatus() != Book.AvailabilityStatus.AVAILABLE &&
                book.getAvailabilityStatus() != Book.AvailabilityStatus.CHECKED_OUT) {
            return ResponseEntity.badRequest().body("Invalid availability status. Use AVAILABLE or CHECKED_OUT.");
        }

        bookRepository.save(book);
        return ResponseEntity.ok("Book added successfully!");
    }

    // View All Books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update book details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
            bookRepository.save(book);
            return ResponseEntity.ok("Book updated successfully!");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}