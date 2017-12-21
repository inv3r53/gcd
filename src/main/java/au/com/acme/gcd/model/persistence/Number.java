package au.com.acme.gcd.model.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Number extends AbstractEntity {

    private String n;

}
