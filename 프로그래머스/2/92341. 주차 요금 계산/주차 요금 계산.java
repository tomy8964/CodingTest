import java.util.*;
class Solution {
    static List<Car> carList;
    public int[] solution(int[] fees, String[] records) {
        int default_time = fees[0];
        int default_fee = fees[1];
        int exceed_time = fees[2];
        int exceed_fee = fees[3];
        
        Map<Integer, Integer> map = new HashMap<>();
        carList = new ArrayList<>();
        for(String r : records){
            String[] split = r.split(" ");
            String[] time_split = split[0].split(":");
            int time = Integer.parseInt(time_split[0]) * 60 + Integer.parseInt(time_split[1]);
            int number = Integer.parseInt(split[1]);
            boolean in = (split[2].equals("IN")) ? true : false;
            
            if(in) {
                map.put(number, time);
            } else {
                int park_time = time - map.get(number);
                // 누적 계산
                if(payed(number) != null) {
                    payed(number).time += park_time;
                    map.remove(number);
                    continue;
                }
                carList.add(new Car(number, park_time));
                map.remove(number);
            }
        }
        
        // 23:59 모든 차 출차
        for( Map.Entry<Integer, Integer> entry : map.entrySet() ){
            Integer number = entry.getKey();
            Integer time = entry.getValue();
            int park_time = 23 * 60 + 59 - map.get(number);
            
            // 누적 계산
            if(payed(number) != null) {
                payed(number).time += park_time;
                continue;
            }
            carList.add(new Car(number, park_time));
        }
        
        
        Collections.sort(carList);
        int[] answer = new int[carList.size()];
        for(int i = 0; i < answer.length; i++){
            int time = carList.get(i).time;
            int fee = 0;
            if(time <= default_time) fee = default_fee;
            else fee = default_fee + (int) Math.ceil((double)(time - default_time) / exceed_time) * exceed_fee;
            answer[i] = fee;
        }
        return answer;
    }
    
    public Car payed(int number) {
        for(Car c : carList){
            if (c.number == number) return c;
        }
        return null;
    }
    
    public static class Car implements Comparable <Car> {
        int number;
        int time;
        
        public Car(int number, int time){
            this.number = number;
            this.time = time;
        }
        
        @Override
        public int compareTo(Car c){
            return this.number - c.number;
        }
    }
}