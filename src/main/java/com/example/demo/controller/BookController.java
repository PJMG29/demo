package com.example.demo.controller;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto request){
        BookResponseDto created = bookService.createBook(request);
        URI location = URI.create("api/books/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchByTitle(keyword));
    }

    /**
     * GET /api/books/low-stock?threshold=5
     * @RequestParam with a default value if the client omits it entirely.
     */
    @GetMapping("/low-stock")
    public ResponseEntity<List<BookResponseDto>> getLowStockBooks(
            @RequestParam(defaultValue = "10") int threshold) {
        return ResponseEntity.ok(bookService.getLowStockBooks(threshold));
    }

    /**
     * PUT /api/books/{id}
     * Idempotent - replaces the ENTIRE book resource with the given data.
     * Calling this 3 times with the same body leaves the book in the same
     * final state as calling it once.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id, @Valid @RequestBody BookRequestDto request) {
        BookResponseDto updated = bookService.updateBook(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/books/{id}
     * Idempotent - deleting an already-deleted (or never-existing) book
     * has the same end state as deleting it once.
     * Returns 204 No Content - success, but nothing meaningful to return.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
