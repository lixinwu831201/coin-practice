
package priv.lixin.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class DataModel {

    @JsonProperty("time")
    public Time time;
    @JsonProperty("disclaimer")
    public String disclaimer;
    @JsonProperty("chartName")
    public String chartName;
    @JsonProperty("bpi")
    public Map<String, BPIModel> bpi;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
