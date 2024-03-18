package algo_sil;

import java.util.*;

public class PGS_주차_요금_계산 {
    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            Map<String, String> map = new HashMap<>();
            Map<String, Integer> fee = new HashMap<>();

            for (int i = 0; i < records.length; i++) {
                fee.put(records[i].split(" ")[1], 0);
            }

            for (int i = 0; i < records.length; i++) {
                String[] info = records[i].split(" ");

                if (map.containsKey(info[1])) {
                    String[] in = map.remove(info[1]).split(":");
                    String[] out = info[0].split(":");

                    int hour = Integer.parseInt(out[0]) - Integer.parseInt(in[0]);
                    int minute = Integer.parseInt(out[1]) - Integer.parseInt(in[1]);

                    fee.replace(info[1], fee.get(info[1]) + 60 * hour + minute);

                } else {
                    map.put(info[1], info[0]);
                }
            }

            for (String key : map.keySet()) {
                String[] inTime = map.get(key).split(":");

                int hour = 23 - Integer.parseInt(inTime[0]);
                int minute = 59 - Integer.parseInt(inTime[1]);

                fee.replace(key, fee.get(key) + 60 * hour + minute);
            }

            List<Map.Entry<String, Integer>> list = new ArrayList(fee.entrySet());
            Collections.sort(list, (o1, o2) -> {
                return Integer.parseInt(o1.getKey()) > Integer.parseInt(o2.getKey()) ? 1 : Integer.parseInt(o1.getKey()) < Integer.parseInt(o2.getKey()) ? -1 : 0;
            });


            int[] answer = new int[list.size()];

            for (int i = 0; i < answer.length; i++) {
                if (list.get(i).getValue() > fees[0]) {
                    answer[i] = fees[1] + (int) Math.ceil((list.get(i).getValue() - fees[0]) / (double) fees[2]) * fees[3];
                } else {
                    answer[i] = fees[1];
                }
            }

            return answer;
        }
    }
}
