
package priv.lixin.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class BPIModel {

    @JsonProperty("code")
    public String code;
    @JsonProperty("symbol")
    public String symbol;
    @JsonProperty("rate")
    public String rate;
    @JsonProperty("description")
    public String description;
    @JsonProperty("rate_float")
    public Double rateFloat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


}
