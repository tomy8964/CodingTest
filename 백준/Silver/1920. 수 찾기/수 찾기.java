
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] split1 = br.readLine().split(" ");
        List<Integer> arr1 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr1.add(Integer.parseInt(split1[i]));
        }
        int M = Integer.parseInt(br.readLine());
        String[] split2 = br.readLine().split(" ");
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(split2[i]);
        }

        Collections.sort(arr1);

        for (int i = 0; i < M; i++) {
            int result = Collections.binarySearch(arr1, arr2[i]);
            result = (result > -1) ? 1 : 0;
            System.out.println(result);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}