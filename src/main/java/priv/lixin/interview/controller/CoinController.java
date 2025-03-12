package priv.lixin.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priv.lixin.interview.model.Coin;
import priv.lixin.interview.service.CoinService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(path = "")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @PostMapping(value = "/find")
    public ResponseEntity<Coin> findByCoinType(HttpServletRequest request, @RequestBody String requestBody) {
        return coinService.findByCoinType(requestBody)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }


}
