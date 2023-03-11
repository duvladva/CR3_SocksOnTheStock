package pro.sky.cr3_socksonthestock.service;

import pro.sky.cr3_socksonthestock.model.Color;
import pro.sky.cr3_socksonthestock.model.Size;
import pro.sky.cr3_socksonthestock.model.SocksBatch;

public interface ValidationService {
    boolean validate (SocksBatch socksBatch);
    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
