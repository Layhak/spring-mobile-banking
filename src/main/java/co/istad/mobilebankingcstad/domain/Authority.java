package co.istad.mobilebankingcstad.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities_tbl")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
}
