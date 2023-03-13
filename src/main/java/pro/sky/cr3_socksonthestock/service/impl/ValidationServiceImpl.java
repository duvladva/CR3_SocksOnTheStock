package pro.sky.cr3_socksonthestock.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.cr3_socksonthestock.model.Color;
import pro.sky.cr3_socksonthestock.model.Size;
import pro.sky.cr3_socksonthestock.model.SocksBatch;
import pro.sky.cr3_socksonthestock.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null
                && socksBatch.getQuantity() >0
                && socksBatch.getSocks().getColor() != null
                && socksBatch.getSocks().getSize() != null
                && checkCotton(socksBatch.getSocks().getCottonPart(), socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        return color != null
                && size != null
                && checkCotton(cottonMin, cottonMax);
    }

    private boolean checkCotton(int cottonMin, int cottonMax) {
        return  cottonMin >= 0 && cottonMax <= 100;
    }
}
