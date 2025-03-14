package priv.lixin.interview.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//使用隨機PORT防止衝突
@AutoConfigureMockMvc
public class ExternalAPIByRestTemplateTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCallExternalAPI() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/external/original");


        ResultActions perform = mockMvc.perform(builder);


        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        perform.andExpect(ok);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        perform.andExpect(contentType);


        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json("{\n" +
                "    \"time\": {\n" +
                "        \"updated\": \"Sep 2, 2024 07:07:20 UTC\",\n" +
                "        \"updatedISO\": \"2024-09-02T07:07:20.000+00:00\",\n" +
                "        \"updateduk\": \"Sep 2, 2024 at 08:07 BST\"\n" +
                "    },\n" +
                "    \"disclaimer\": \"just for test\",\n" +
                "    \"chartName\": \"Bitcoin\",\n" +
                "    \"bpi\": {\n" +
                "        \"USD\": {\n" +
                "            \"code\": \"USD\",\n" +
                "            \"symbol\": \"&#36;\",\n" +
                "            \"rate\": \"57,756.298\",\n" +
                "            \"description\": \"United States Dollar\",\n" +
                "            \"rate_float\": 57756.2984\n" +
                "        },\n" +
                "        \"GBP\": {\n" +
                "            \"code\": \"GBP\",\n" +
                "            \"symbol\": \"&pound;\",\n" +
                "            \"rate\": \"43,984.02\",\n" +
                "            \"description\": \"British Pound Sterling\",\n" +
                "            \"rate_float\": 43984.0203\n" +
                "        },\n" +
                "        \"EUR\": {\n" +
                "            \"code\": \"EUR\",\n" +
                "            \"symbol\": \"&euro;\",\n" +
                "            \"rate\": \"52,243.287\",\n" +
                "            \"description\": \"Euro\",\n" +
                "            \"rate_float\": 52243.2865\n" +
                "        }\n" +
                "    }\n" +
                "}");
        perform.andExpect(result);


    }

    @Test
    public void testCallExternalAPIAndTransform() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/external/transform");


        ResultActions perform = mockMvc.perform(builder);


        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        perform.andExpect(ok);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        perform.andExpect(contentType);

        String responseContent = perform.andReturn().getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json("{\n" +
                "    \"updateTime\": \"2024/09/02 07:07:20\",\n" +
                "    \"coinDetailList\": [\n" +
                "        {\n" +
                "            \"coinType\": \"USD\",\n" +
                "            \"coinChineseName\": \"美金\",\n" +
                "            \"rateFloat\": 57756.2984\n" +
                "        },\n" +
                "        {\n" +
                "            \"coinType\": \"GBP\",\n" +
                "            \"coinChineseName\": \"英鎊\",\n" +
                "            \"rateFloat\": 43984.0203\n" +
                "        },\n" +
                "        {\n" +
                "            \"coinType\": \"EUR\",\n" +
                "            \"coinChineseName\": \"歐元\",\n" +
                "            \"rateFloat\": 52243.2865\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        perform.andExpect(result);
    }

}
