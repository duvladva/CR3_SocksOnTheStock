package pro.sky.cr3_socksonthestock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.cr3_socksonthestock.controller.dto.ResponseDto;
import pro.sky.cr3_socksonthestock.model.Color;
import pro.sky.cr3_socksonthestock.model.Size;
import pro.sky.cr3_socksonthestock.model.SocksBatch;
import pro.sky.cr3_socksonthestock.service.SocksStoreService;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков", description = "Регистрация прихода, отпуск со склада, списание брака, подсчет количества")
@RequiredArgsConstructor
public class SocksStoreController {

    private final SocksStoreService storeService;

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад")
    @ApiResponse(responseCode = "200", description = "Операция успешна")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> accept(@RequestBody SocksBatch socksBatch) { // Dto - это data transfer object
        storeService.accept(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Носки успешно добавлены на склад"));
    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков со склада")
    @ApiResponse(responseCode = "200", description = "Операция успешна")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> issuance(@RequestBody SocksBatch socksBatch) {
        int socksCount = storeService.issuance(socksBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount + " носков отпущено со склада"));

    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.")
    @ApiResponse(responseCode = "200", description = "Операция успешна")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> getCount(@RequestParam Color color, // в get-запросах используются параметры
                                         @RequestParam Size size,
                                         @RequestParam int cottonMin,
                                         @RequestParam int cottonMax) {
        int socksCount = storeService.getCount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponseDto(" Количество носков: " + socksCount));
    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200", description = "Операция успешна")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> reject(@RequestBody SocksBatch socksBatch) {
        int socksCount = storeService.reject(socksBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount + " носков списано со склада"));

    }
}
