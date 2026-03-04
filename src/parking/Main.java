package parking;


import java.util.*;

public class Main {
    static void main(String[] args) {
        List<Map<String,String>> cars = new ArrayList<>();
        while (true){
            System.out.println("***Parkingə xoş gəlmisiniz***");
            System.out.println("Zəhmət olmasa, avtomobilinizin nömrəsini daxil edin.");
            Scanner scanner = new Scanner(System.in);
            String avtomobilNomre = scanner.nextLine();
            Park park = new Park(cars, avtomobilNomre);

            boolean isCarParked = false;
            for (Map<String,String> car:cars){
                if (car.get("plateNumber").equals(avtomobilNomre)){
                    isCarParked = true;
                    break;
                }
            }
            if (isCarParked == true){
                park.parkOut();
            }else {
                park.ParkIn();
            }
        }
    }
}

