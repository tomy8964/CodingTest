import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int K = Integer.parseInt(split[0]);
        int N = Integer.parseInt(split[1]);

        long end = 0;

        List<Long> lans = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            lans.add(Long.parseLong(br.readLine()));
            if (lans.get(i) > end) {
                end = lans.get(i);
            }
        }

        Collections.sort(lans);

        // 0, 0 인 경우 생각
        end++;
        long start = 0;

        while (start < end) {
            long mid = (start + end) / 2;
            long sum = 0;

            for (Long lan : lans) {
                sum += lan / mid;
            }

            // 랜선의 총 갯수가 N개 보다 적다
            // 랜선의 길이를 줄여야 한다.
            if (sum < N) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);

        bw.flush();
        bw.close();
        br.close();
    }
}
