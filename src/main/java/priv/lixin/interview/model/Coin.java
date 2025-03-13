package priv.lixin.interview.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coinNo;
    private String coinType;
    private String coinChineseName;

}

