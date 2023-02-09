package service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    @Id
    private String rewardId;
    private String name;
    private String quantity;
    private String type;//ELEMENT, IN_SCHOOL, GIF;
    private Double price;

}
