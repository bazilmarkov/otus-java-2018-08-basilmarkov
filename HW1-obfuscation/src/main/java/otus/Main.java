package otus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Main {

    public static void main(String[] args) {
        try {
            Multiset<String> multiset = HashMultiset.create();
            multiset.add("String from guava multiset");
            multiset.add("Another string from guava multiset");
            for (String word : multiset) {
                System.out.println(word);
            }
            new BytecodeExample("Basil").printName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
