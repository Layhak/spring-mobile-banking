package co.istad.mobilebankingcstad.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles_tbl")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    // stuff , admin , ...
    @Column(nullable = false, length = 25)
    private String name;
}
