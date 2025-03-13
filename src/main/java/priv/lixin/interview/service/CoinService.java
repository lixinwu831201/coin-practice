package priv.lixin.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.lixin.interview.model.Coin;
import priv.lixin.interview.repository.CoinRepository;

import java.util.List;

@Service
@Transactional
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public Long getCoinNoByCoinType(String coinType) {
        Coin coin = coinRepository.findByCoinType(coinType);
        try {
            return coin.getCoinNo();
        } catch (Exception e) {
            return null;
        }

    }

    public Coin saveCoin(Coin coin) {
        return coinRepository.save(coin);
    }

    public Coin updateCoin(Long id, Coin coin) {
        coin.setCoinNo(id);
        return coinRepository.save(coin);
    }

    public void deleteCoin(Long id) {
        coinRepository.deleteById(id);
    }


}
