package SocialFb.Models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(name ="reactions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToOne( fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;

    @CreationTimestamp
    private Date createdAt;

    @Override
    public boolean equals (Object o) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass(this) != Hibernate.getClass(o) ) return false;
        Reaction reaction = (Reaction) o;

        return Objects.equals(id, reaction.id);
    }

    @Override
    public int hashCode () {
        return 1963451055;
    }
}
