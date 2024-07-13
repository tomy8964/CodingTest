class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int x = 5, y = 5;
        boolean[][][] visited = new boolean[11][11][4];
        for(int i = 0; i < dirs.length(); i++){
            char c = dirs.charAt(i);
            int direction = 0;
            int newX = 0, newY = 0;
            if (c == 'L') {
                newX = x - 1;
                newY = y;
                direction = 1;
            }
            else if (c == 'R') {
                newX = x + 1;
                newY = y;
                direction = 3;
            }
            else if (c == 'U') {
                newY = y - 1;
                newX = x;
                direction = 2;
            }
            else {
                newY = y + 1;
                newX = x;
            }
            
            if(newX > 10 || newY > 10 || newX < 0 || newY < 0) continue;
            
            if(!visited[newY][newX][direction]){
                System.out.println(newY + " " + newX + " " + direction);
                visited[newY][newX][direction] = true;
                if(direction < 2) direction += 2;
                else direction -= 2;
                visited[y][x][direction] = true;
                answer++;
            }
            x = newX;
            y = newY;
        }
        return answer;
    }
}