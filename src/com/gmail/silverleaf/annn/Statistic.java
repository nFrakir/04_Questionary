package com.gmail.silverleaf.annn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {
    private final static String TEMPLATE = "<table>\n" +
                    "          <thead>\n" +
                    "          <th class=\"label\"></th>\n" +
                    "          <th></th>\n" +
                    "          <th></th>\n" +
                    "          </thead>\n" +
                    "          <tbody>\n" +
                    "            <tr>\n" +
                    "              <td><label for=\"likeJava\">Do you like Java?</label></td>\n" +
                    "              <td>Yes: %s</td>\n" +
                    "              <td>No: %s</td>\n" +
                    "            </tr>\n" +
                    "\n" +
                    "            <tr>\n" +
                    "              <td><label for=\"skilled\">Are you skilled in another languages?</label></td>\n" +
                    "              <td>Yes: %s</td>\n" +
                    "              <td>No: %s</td>\n" +
                    "\n" +
                    "            <tr>\n" +
                    "              <td><label for=\"knowHtml\">Do you know HTML?</label></td>\n" +
                    "              <td>Yes: %s</td>\n" +
                    "              <td>No: %s</td>\n" +
                    "            </tr>\n" +
                    "          </tbody>\n" +
                    "        </table>";
    private Map<String, Integer[]> voites = new HashMap<>();
    private static Statistic statistic = new Statistic();

    private Statistic() {
        super();

        Integer[] cnt1 = {0, 0};
        voites.put("likeJava", cnt1);
        Integer[] cnt2 = {0, 0};
        voites.put("skilled", cnt2);
        Integer[] cnt3 = {0, 0};
        voites.put("knowHtml", cnt3);
    }

    public static Statistic getInstance() {
        return statistic;
    }

    public synchronized void addAnswer(String answer, int index) {
        Integer[] cnt = voites.get(answer);
        cnt[index] += 1;
        voites.put(answer, cnt);
    }

    @Override
    public synchronized String toString() {
        return String.format(TEMPLATE,
                voites.get("likeJava")[0], voites.get("likeJava")[1],
                voites.get("skilled")[0], voites.get("skilled")[1],
                voites.get("knowHtml")[0], voites.get("knowHtml")[1]
                );
    }
}
