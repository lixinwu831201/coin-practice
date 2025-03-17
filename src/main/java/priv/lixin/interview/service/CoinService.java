package priv.lixin.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.lixin.interview.dto.BPIModel;
import priv.lixin.interview.dto.CoinDetail;
import priv.lixin.interview.dto.DataModel;
import priv.lixin.interview.dto.ResponseDTO;
import priv.lixin.interview.model.Coin;
import priv.lixin.interview.repository.CoinRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
// 未做錯誤處理
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

    public ResponseDTO mergeExternalAPI(DataModel dataModel) {

        List<Coin> coinList = coinRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        // 設定更新時間
        responseDTO.setUpdateTime(dataModel.getTime().getUpdatedISO());
        // 用組合資料庫中幣別與外部API
        Map<String, BPIModel> bpi = dataModel.getBpi();
        List<CoinDetail> tempList = new ArrayList<>();

        for (Coin coin : coinList) {
            CoinDetail coinDetail = new CoinDetail();
            coinDetail.setRateFloat(bpi.get(coin.getCoinType()).getRateFloat());
            coinDetail.setCoinChineseName(coin.getCoinChineseName());
            coinDetail.setCoinType(coin.getCoinType());
            tempList.add(coinDetail);

        }

        responseDTO.setCoinDetailList(tempList);
        return responseDTO;
    }
}
