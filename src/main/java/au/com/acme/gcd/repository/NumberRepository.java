package au.com.acme.gcd.repository;

import au.com.acme.gcd.model.persistence.Number;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepository extends JpaRepository<Number,Long> {
}
