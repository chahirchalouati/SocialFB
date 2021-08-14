package SocialFb.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Table(name ="replies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    private String content;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reaction> reactions;

    @CreationTimestamp
    private Date createdAt;


}
