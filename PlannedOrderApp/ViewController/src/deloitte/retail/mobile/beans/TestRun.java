package deloitte.retail.mobile.beans;

import java.util.HashMap;

public class TestRun {
    public TestRun() {
        super();
    }

    public static void main(String[] args) {
        TestRun testRun = new TestRun();
//        String s ="";
//        System.out.println("Index:"+s.indexOf("(")+" Last:"+s.indexOf(")"));
//        System.out.println("String:"+s.substring(0,s.indexOf("("))+" Number:"+
//                           s.substring(s.indexOf("(")+1, s.indexOf(")")));
        HashMap h = new HashMap();
        h.put("100", "One");
        h.put("200", "Two");
        System.out.println(h.toString());
        String s = (String)h.get(100);
        System.out.println(s);
    }
}
