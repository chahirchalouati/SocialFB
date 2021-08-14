package SocialFb.Models;

import lombok.*;

import javax.persistence.*;

@Table(name = "action_type")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String type;

}