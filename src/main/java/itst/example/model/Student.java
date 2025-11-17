package itst.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_number", nullable = false, length = 32)
    private Integer controlNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "lastname", nullable = false, length = 120)
    private String lastname;

    // Lado inverso de la relación 1:1
    @OneToOne(
        mappedBy = "student",
        cascade = CascadeType.ALL,      // refleja el ON DELETE CASCADE del esquema
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Address address;
}