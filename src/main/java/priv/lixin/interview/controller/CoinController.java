package priv.lixin.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import priv.lixin.interview.model.Coin;
import priv.lixin.interview.service.CoinService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;


    @GetMapping
    public List<Coin> getAllCoins() {
        return coinService.getAllCoins();
    }


    @PostMapping
    public ResponseEntity<Coin> createCoin(@RequestBody Coin coin) {

        if (coinService.getCoinNoByCoinType(coin.getCoinType()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(coinService.saveCoin(coin));
    }

    @PutMapping("/{coinType}")
    public ResponseEntity<Coin> updateCoin(@PathVariable String coinType, @RequestBody Coin coin) {
        Long coinNo = coinService.getCoinNoByCoinType(coin.getCoinType());
        if (coinNo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coinService.updateCoin(coinNo, coin));
    }

    @DeleteMapping("/{coinType}")
    public ResponseEntity<Void> deleteCoin(@PathVariable String coinType) {
        Long coinNo = coinService.getCoinNoByCoinType(coinType);
        if (coinNo == null) {
            return ResponseEntity.notFound().build();
        }
        coinService.deleteCoin(coinNo);
        return ResponseEntity.noContent().build();
    }


}
