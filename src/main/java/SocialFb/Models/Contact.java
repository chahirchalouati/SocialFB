package SocialFb.Models;

import SocialFb.Enums.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Table(name ="contacts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private ContactTypeEnum type;

    @NotBlank(message = "type contact can't be blank")
    private String value ;

    @CreationTimestamp
    private Date createdAt;

}
