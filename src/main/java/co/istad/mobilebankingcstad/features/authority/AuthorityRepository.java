package co.istad.mobilebankingcstad.features.authority;

import co.istad.mobilebankingcstad.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
