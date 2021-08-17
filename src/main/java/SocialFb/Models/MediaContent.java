package SocialFb.Models;

import SocialFb.Enums.ContentMediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(name ="multimediaContents")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MediaContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private ContentMediaType contentMediaType;
    @Lob
    private String url;
    @ManyToOne(fetch = FetchType.EAGER)
    private Src src;

}