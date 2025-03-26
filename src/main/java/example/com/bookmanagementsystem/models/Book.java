package example.com.bookmanagementsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 255, message = "Title length must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(min = 1, max = 255, message = "Author length must be between 1 and 255 characters")
    private String author;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Availability status cannot be null")
    private AvailabilityStatus availabilityStatus;

    public enum AvailabilityStatus {
        AVAILABLE,
        CHECKED_OUT
    }
}
