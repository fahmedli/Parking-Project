package parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Park {
    List<Map<String,String>> cars;
    String avtomobilNomre;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Park(List<Map<String,String>> cars, String avtomobilNomre){
        this.avtomobilNomre=avtomobilNomre;
        this.cars = cars;
    }

    public void ParkIn(){
        Map<String,String> car = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();
        String formatterdDate = currentTime.format(formatter);

        car.put("id", "1");
        car.put("plateNumber", avtomobilNomre);
        car.put("enteredDate", formatterdDate);
        cars.add(car);
        System.out.println("---------------------------------");
        System.out.println(avtomobilNomre + " nömrəli avtomobil uğurla " + car.get("enteredDate") + " tarixinədək park olundu.");
        System.out.println("---------------------------------");

    }
    public void parkOut(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zəhmət olmasa, ödəniş edin: ");
        Map<String,String> parkedCar = null;
        for (Map<String,String> car:cars){
            if (car.get("plateNumber").equals(avtomobilNomre)){
                parkedCar = car;
                break;
            }
        }
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime enteredData = LocalDateTime.parse(parkedCar.get("enteredDate"), formatter);
        Duration duration = Duration.between(enteredData, currentTime);
        long seconds = duration.getSeconds();
        double odenilecekMebleg = seconds*0.01;

        System.out.println("Ödənilməli məbləğ: " + odenilecekMebleg + " AZN");
        payment(odenilecekMebleg);


        // Uğurlu ödənişdən sonra, istifadəçiyə 5 saniyə vaxt verilir.
        double elaveOdenis = 0.0;
        while (true){
            System.out.println("Zəhmət olmasa, çıxış üçün 'q' simvolunu daxil edin.");
            long baslamaVaxti = System.currentTimeMillis();
            boolean istifadeciQYazdi = false;
            String simvol = scanner.nextLine();
            while ((System.currentTimeMillis() - baslamaVaxti)/1000<5){
                if(simvol.equals("q")){
                    istifadeciQYazdi=true;
                    System.out.println("Avtomobil uğurla parkdan çıxdı. Əlavə ödənməli məbləğ: " +elaveOdenis + " AZN. Yaxşı yol." );
                    System.out.println(parkedCar.get("plateNumber") + " nömrəli avtomobil sistemdən silindi.");
                    cars.remove(parkedCar);
                    return;
                }
            }
            if (!istifadeciQYazdi){
                long odenisVaxti = (System.currentTimeMillis() - baslamaVaxti)/1000;
                elaveOdenis = odenisVaxti*0.01;
                System.out.println("Vaxtında çıxmadınız. Hər saniyə üçün 0.01 AZN ödəyəcəksiniz: " + (elaveOdenis-0.05) + " AZN");
                payment(elaveOdenis-0.05);
                System.out.println(parkedCar.get("plateNumber") + " nömrəli avtomobil sistemdən silindi.");
                cars.remove(parkedCar);
                break;
            }
        }
    }
    public  void payment( double odenilecekMebleg){
        Scanner scanner = new Scanner(System.in);
        double toplamOdenilenMebleg = 0.0;
        while (toplamOdenilenMebleg < odenilecekMebleg){
            System.out.println("Zəhmət olmasa, ödəniləcək məbləği daxil edin: ");
            if (scanner.hasNextDouble()){
                double odenilenMebleg = scanner.nextDouble();
                toplamOdenilenMebleg += odenilenMebleg;
                if (toplamOdenilenMebleg<odenilecekMebleg){
                    System.out.println("Məbləğ tam ödənmədi. Yerdə qalan məbləğ " + (odenilecekMebleg - toplamOdenilenMebleg) + " AZN");
                } else if (toplamOdenilenMebleg>odenilecekMebleg) {
                    System.out.println("Ödəniş uğurla bitdi. Qaliq: " + (toplamOdenilenMebleg-odenilecekMebleg) + " AZN. Yaxşı yol.");
                    toplamOdenilenMebleg = 0.0;
                    break;
                }else {
                    System.out.println("Ödəniş uğurla bitdi. Yaxşı yol.");
                    toplamOdenilenMebleg = 0.0;
                    break;
                }
            }
            else {
                System.out.println("Səhv giriş. Zəhmət olmasa, düzgün məbləğ daxil edin.");
                scanner.next();
            }
        }

    }
}
