
package priv.lixin.interview.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Time {

    @JsonProperty("updated")
    public String updated;
    @JsonProperty("updatedISO")
    public Date updatedISO;
    @JsonProperty("updateduk")
    public String updateduk;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


}
