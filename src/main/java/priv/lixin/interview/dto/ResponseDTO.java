package priv.lixin.interview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseDTO {

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonProperty("updateTime")
    public Date updateTime;

    @JsonProperty("coinDetailList")
    public List<CoinDetail> coinDetailList;

}
