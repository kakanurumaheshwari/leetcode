import java.util.*;

class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        // Step 1: Initialize
        for (List<String> acc : accounts) {
            String name = acc.get(0);

            for (int i = 1; i < acc.size(); i++) {
                parent.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        // Step 2: Union emails
        for (List<String> acc : accounts) {
            String firstEmail = acc.get(1);

            for (int i = 2; i < acc.size(); i++) {
                union(firstEmail, acc.get(i), parent);
            }
        }

        // Step 3: Group emails
        Map<String, TreeSet<String>> map = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(email, parent);
            map.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
        }

        // Step 4: Build result
        List<List<String>> result = new ArrayList<>();

        for (String root : map.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(owner.get(root));
            list.addAll(map.get(root));
            result.add(list);
        }

        return result;
    }

    private String find(String s, Map<String, String> parent) {
        if (!parent.get(s).equals(s)) {
            parent.put(s, find(parent.get(s), parent));
        }
        return parent.get(s);
    }

    private void union(String a, String b, Map<String, String> parent) {
        String pa = find(a, parent);
        String pb = find(b, parent);
        if (!pa.equals(pb)) {
            parent.put(pa, pb);
        }
    }
}