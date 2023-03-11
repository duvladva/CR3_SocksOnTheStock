package pro.sky.cr3_socksonthestock.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.cr3_socksonthestock.exception.ValidationException;
import pro.sky.cr3_socksonthestock.model.Color;
import pro.sky.cr3_socksonthestock.model.Size;
import pro.sky.cr3_socksonthestock.model.Socks;
import pro.sky.cr3_socksonthestock.model.SocksBatch;
import pro.sky.cr3_socksonthestock.repository.SocksRepository;
import pro.sky.cr3_socksonthestock.service.SocksStoreService;
import pro.sky.cr3_socksonthestock.service.ValidationService;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksStoreServiceImpl implements SocksStoreService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    @Override
    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int issuance(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {

        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax
            ) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();

        }

    }
}
