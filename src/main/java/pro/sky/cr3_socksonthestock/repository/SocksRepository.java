package pro.sky.cr3_socksonthestock.repository;

import pro.sky.cr3_socksonthestock.model.Socks;
import pro.sky.cr3_socksonthestock.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {
    void save(SocksBatch socksBatch);
    int remove(SocksBatch socksBatch);
    Map<Socks, Integer> getAll();

}
