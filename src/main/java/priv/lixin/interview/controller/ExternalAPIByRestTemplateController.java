package priv.lixin.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import priv.lixin.interview.dto.DataModel;
import priv.lixin.interview.dto.ResponseDTO;
import priv.lixin.interview.service.CoinService;

@RestController
@CrossOrigin
@RequestMapping(path = "external")
public class ExternalAPIByRestTemplateController {


    private final RestTemplate restTemplate;

    @Autowired
    private CoinService coinService;

    public ExternalAPIByRestTemplateController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @GetMapping("/original")
    public ResponseEntity<?> callExternalAPI() {
        String uri = "https://kengp3.github.io/blog/coindesk.json";
        return ResponseEntity.ok(this.restTemplate.getForObject(uri, DataModel.class));
    }

    @GetMapping("/transform")
    public ResponseEntity<?> callExternalAPIAndTransform() {
        String uri = "https://kengp3.github.io/blog/coindesk.json";
        ResponseDTO responseDTO = coinService.mergeExternalAPI(this.restTemplate.getForObject(uri, DataModel.class));
        return ResponseEntity.ok(responseDTO);
    }


}
