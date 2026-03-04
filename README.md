# Parking-Project

**Parking-Project** Java ilə hazırlanmış sadə park sistemi layihəsidir. Bu layihə istifadəçilərin avtomobillərini park etməsi və ödəniş etməsini idarə edir.

## Funksionallıqlar

- Avtomobil park etmə (`ParkIn`)
- Avtomobil çıxışı və ödəniş (`parkOut`)
- Ödəniş sistemi: vaxt əsasında avtomatik məbləğ hesablanır
- İstifadəçi rahatlığı üçün mesajlar və əlavə ödəniş xəbərdarlıqları

## Fayl Strukturu
src/
└─ parking/
├─ Main.java
└─ Park.java

- **Main.java** – Proqramın əsas girişi və loop funksiyası  
- **Park.java** – Avtomobilin park edilməsi, çıxışı və ödənişlərin idarəsi

## İstifadə Qaydası

1. Layihəni IntelliJ-də açın və ya terminaldan compile edin:
```bash
javac src/parking/*.java
Proqramı işə salın:
java parking.Main
