class Solution {
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 1. 모든 시간을 '총 초'로 변환
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        int answer = 0;
        
        // 2. 시작 시점(t)에서의 겹침 확인
        // (12:00:00 또는 00:00:00)
        if (checkStartOverlap(startTime)) {
            answer = 1;
        }

        // 3. (start + 1)초부터 end초까지 1초씩 순회
        // (t-1)초와 (t)초 사이의 [구간]을 검사
        for (int t = startTime + 1; t <= endTime; t++) {
            
            // t-1초 (이전) 상태의 각도
            double[] prevAngles = getAngles(t - 1);
            double prevH = prevAngles[0];
            double prevM = prevAngles[1];
            double prevS = prevAngles[2];
            
            // t초 (현재) 상태의 각도
            double[] currAngles = getAngles(t);
            double currH = currAngles[0];
            double currM = currAngles[1];
            double currS = currAngles[2];

            boolean meetH = false; // 시침과 겹쳤는가
            boolean meetM = false; // 분침과 겹쳤는가
            
            // 4. 추월 감지 (시침)
            if (currS < prevS) { // 초침이 360도를 넘어 0도로 돌아간 경우 (S-Wrap)
                if (prevS < prevH) meetH = true;
            } else { // 일반 경우
                if (prevS < prevH && currS >= currH) meetH = true;
            }

            // 5. 추월 감지 (분침)
            if (currS < prevS) { // S-Wrap
                if (prevS < prevM) meetM = true;
            } else { // 일반 경우
                if (prevS < prevM && currS >= currM) meetM = true;
            }
            
            // 6. 겹침 횟수 카운트
            if (meetH && meetM) {
                // 시침, 분침, 초침이 정확히 같은 각도(e.g., 12:00:00)에서 만난 경우
                // (시작 시점에 이미 1을 더했으므로, 이 경우는 중복)
                // 하지만 t-1 -> t (e.g., 11:59:59 -> 12:00:00) 구간에서
                // currH == currM == currS (전부 0도) 이면 1번만 카운트
                if (currH == currM) {
                    answer++;
                } else {
                    // 예제 6번처럼 1초 구간 내에서 시침/분침을 따로따로 추월한 경우
                    answer += 2;
                }
            } else if (meetH || meetM) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // (h, m, s)를 입력받아 각도 배열 [시, 분, 초]를 반환
    private double[] getAngles(int h, int m, int s) {
        double sAngle = s * 6.0;
        double mAngle = m * 6.0 + s * 0.1;
        double hAngle = (h % 12) * 30.0 + m * 0.5 + s * (1.0 / 120.0);
        
        return new double[] { hAngle, mAngle, sAngle };
    }

    // '총 초'를 (h, m, s)로 변환하고 각도를 계산
    private double[] getAngles(int totalSeconds) {
        int h = totalSeconds / 3600;
        int m = (totalSeconds % 3600) / 60;
        int s = totalSeconds % 60;
        
        return getAngles(h, m, s);
    }
    
    // 0시/12시 정각 확인 (시작점이 정각일 때 1번만 카운트하기 위함)
    private boolean checkStartOverlap(int totalSeconds) {
        double[] angles = getAngles(totalSeconds);
        // 0.0 == 0.0 이므로 double 비교 가능
        return (angles[0] == angles[2] || angles[1] == angles[2]);
    }
}