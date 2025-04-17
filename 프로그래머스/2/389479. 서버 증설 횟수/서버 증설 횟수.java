class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24];
            
        for(int i = 0; i < players.length; i++){
            if(players[i] < m) continue;
            // 현재 서버 수가 현재 플레이어 수에 비해 부족하다
            if(servers[i] * m < players[i]) {
                int requiredServers = players[i] / m - servers[i];
                answer += requiredServers;
                for(int j = 0; j < k; j++){
                    if(i+j >= 24) break;
                    servers[i+j] += requiredServers;
                }
            }
        }
        
        return answer;
    }
}