package SocialFb.Models;

import SocialFb.Enums.AttachmentType;
import SocialFb.Utils.Src;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private AttachmentType attachmentType;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Src src;




}
