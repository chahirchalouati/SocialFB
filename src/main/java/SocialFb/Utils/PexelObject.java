package SocialFb.Utils;

import SocialFb.Models.Src;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PexelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String width;
    private String height;
    private String url;
    private String photographer;
    private String photographer_id;
    private String photographer_url;
    private String avg_color;
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Src src;
    private Boolean liked;

}
