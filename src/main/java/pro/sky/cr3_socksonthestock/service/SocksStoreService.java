package pro.sky.cr3_socksonthestock.service;

import pro.sky.cr3_socksonthestock.model.Color;
import pro.sky.cr3_socksonthestock.model.Size;
import pro.sky.cr3_socksonthestock.model.SocksBatch;

public interface SocksStoreService {
    void  accept(SocksBatch socksBatch);
    int issuance(SocksBatch socksBatch);
    int reject(SocksBatch socksBatch);
    int getCount(Color color, Size size, int cottonMin, int cottonMax);

}
