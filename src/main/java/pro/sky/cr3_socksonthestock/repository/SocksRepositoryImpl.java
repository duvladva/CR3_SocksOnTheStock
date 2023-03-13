package pro.sky.cr3_socksonthestock.repository;

import org.springframework.stereotype.Repository;
import pro.sky.cr3_socksonthestock.model.Socks;
import pro.sky.cr3_socksonthestock.model.SocksBatch;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository{

    private Map<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }

    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if(socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);

            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, quantity - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            }else {
                socksMap.remove(socks);
                return quantity;
            }
        }

        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
