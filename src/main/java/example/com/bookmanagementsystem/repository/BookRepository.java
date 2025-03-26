package example.com.bookmanagementsystem.repository;


import example.com.bookmanagementsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    Optional<Book> findById(Long id);

    boolean existsByTitle(String title);

}
