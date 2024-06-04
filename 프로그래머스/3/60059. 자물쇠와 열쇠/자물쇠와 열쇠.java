import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        // key 좌표 입력
        List<Point> keyList = new ArrayList<>();
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                if(key[i][j] == 1) keyList.add(new Point(i, j));
            }
        }
        // lock 좌표 입력
        List<Point> lockList = new ArrayList<>();
        for(int i = 0; i < lock.length; i++){
            for(int j = 0; j < lock.length; j++){
                if(lock[i][j] == 0) lockList.add(new Point(i, j));
            }
        }
        
        if(lockList.size() < 1) return true;
        
        Collections.sort(keyList);
        Collections.sort(lockList);
        
        int firstLockX = lockList.get(0).x;
        int firstLockY = lockList.get(0).y;
        
        int firstKeyX = keyList.get(0).x;
        int firstKeyY = keyList.get(0).y;
        for(Point p : keyList){
            p.x -= firstKeyX;
            p.y -= firstKeyY;
        }
        
        // 90도씩 회전하면서 맞는지 확인
        for(int i = 0; i < 4; i++) {
            
            // 키를 움직이면서 맞는지 확인
            for(Point p : keyList){
                int count = 0;
                int moveX = firstLockX - p.x;
                int moveY = firstLockY - p.y;
                System.out.println("----");
                for(Point q : keyList){
                    int movedX = q.x + moveX;
                    int movedY = q.y + moveY;
                    System.out.println(movedX + " " + movedY);
                    if(movedX < 0 || movedY < 0 || movedX >= lock[0].length || movedY >= lock[0].length) continue;
                    if(lock[movedX][movedY] != 0)  break;
                    
                    count++;
                    System.out.println(count);
                }
                if (lockList.size() == count) return true;
                System.out.println("----");
            }
                
            //90도 회전
            for(Point p : keyList){
                int tmp = p.x;
                p.x = -p.y;
                p.y = tmp;
            }
        }
        
        return false;
    
    }
    
    public class Point implements Comparable<Point> {
        int x;
        int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Point p2){
            if(x == p2.x) return y - p2.y;
            return x - p2.x;
        }
    }
}