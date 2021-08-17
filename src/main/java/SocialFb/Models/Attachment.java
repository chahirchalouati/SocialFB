package SocialFb.Models;

import SocialFb.Enums.AttachmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Enumerated
    private AttachmentType attachmentType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Src src;




}
