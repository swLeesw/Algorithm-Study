package algo_sil;

class PGS_150368 {
    static int[] moneyPercent = {0, 10, 20, 30, 40};
    static int membership = 0, memberPrice = 0, min = Integer.MAX_VALUE;
    static class Solution {
        public int[] solution(int[][] users, int[] emoticons) {
            for (int[] user : users) {
                min = Math.min(min, user[0]);
            }
            for (int i = 0; i < 5; i++) {
                if (min <= moneyPercent[i]) {
                    min = i;
                    break;
                }
            }
            int[] saleMoney = new int[emoticons.length];
            comb(saleMoney, 0, emoticons.length, users, emoticons);
            int[] answer = {membership, memberPrice};
            return answer;
        }

        private void comb(int[] saleMoney, int cnt, int m, int[][] users, int[] emoticons) {
            if (cnt == m) {
                getMoney(users, emoticons, saleMoney);
                return;
            }

            for (int i = cnt; i < m; i++) {
                for (int j = min; j < 5; j++) {
                    saleMoney[i] = moneyPercent[j];
                    comb(saleMoney, i + 1, m, users, emoticons);
                }
            }
        }

        private void getMoney(int[][] users, int[] emoticons, int[] saleMoney) {
            int member = 0;
            int price = 0;

            for (int[] user : users) {
                int minSale = user[0];
                int limitPrice = user[1];
                int sum = 0;

                for (int i = 0; i < saleMoney.length; i++) {
                    if (saleMoney[i] < minSale) continue;
                    sum += getPrice(emoticons[i], saleMoney[i]);
                }

                // 기준점 이상이면 가입, 아니면 구입
                if (limitPrice <= sum) member++;
                else price += sum;
            }

            // static 변수 업데이트
            if (member > membership) {
                membership = member;
                memberPrice = price;
            } else if (member == membership && price > memberPrice) {
                memberPrice = price;
            }
        }

        private int getPrice(int price, int percent) {
            return (price / 100) * (100 - percent);
        }
    }
}