package priv.lixin.interview.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class CoinControllerTests {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllCoins() throws Exception {
        // 拆解版本
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/coins");


        ResultActions perform = mockMvc.perform(builder);


        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        perform.andExpect(ok);

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        perform.andExpect(contentType);


        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json("[\n" +
                "    {\n" +
                "        \"coinNo\": 1,\n" +
                "        \"coinType\": \"USD\",\n" +
                "        \"coinChineseName\": \"美金\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"coinNo\": 2,\n" +
                "        \"coinType\": \"GBP\",\n" +
                "        \"coinChineseName\": \"英鎊\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"coinNo\": 3,\n" +
                "        \"coinType\": \"EUR\",\n" +
                "        \"coinChineseName\": \"歐元\"\n" +
                "    }\n" +
                "]");
        perform.andExpect(result);


    }

    @Test
    public void testCreateCoin() throws Exception {

        String coin = "    {\n" +
                "        \"coinType\": \"TWD\",\n" +
                "        \"coinChineseName\": \"台幣\"\n" +
                "    }";


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/coins").content(coin).contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        perform.andExpect(contentType);


        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json(

                "    {\n" +
                        "        \"coinNo\": 4,\n" +
                        "        \"coinType\": \"TWD\",\n" +
                        "        \"coinChineseName\": \"台幣\"\n" +
                        "    }"
        );
        perform.andExpect(result);


    }

    @Test
    public void testUpdateCoin() throws Exception {

        String coin = "    {\n" +
                "        \"coinType\": \"USD\",\n" +
                "        \"coinChineseName\": \"美金1\"\n" +
                "    }";


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.put("/api/coins/{coinType}", "USD").content(coin).contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(MockMvcResultMatchers.status().isOk());

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        perform.andExpect(contentType);


        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json(

                "    {\n" +
                        "        \"coinNo\": 1,\n" +
                        "        \"coinType\": \"USD\",\n" +
                        "        \"coinChineseName\": \"美金1\"\n" +
                        "    }"
        );
        perform.andExpect(result);


    }

    @Test
    public void testDeleteCoin() throws Exception {


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/api/coins/{coinType}", "GBP"));

        perform.andExpect(MockMvcResultMatchers.status().is(204));

        ResultActions perform2 = mockMvc.perform(MockMvcRequestBuilders.delete("/api/coins/{coinType}", "TWD"));

        perform2.andExpect(MockMvcResultMatchers.status().is(404));


    }
}
