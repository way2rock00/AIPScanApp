package deloitte.retail.mobile.beans;

public class TestRun {
    public TestRun() {
        super();
    }

    public static void main(String[] args) {
        TestRun testRun = new TestRun();
        String s ="";
        System.out.println("Index:"+s.indexOf("(")+" Last:"+s.indexOf(")"));
        System.out.println("String:"+s.substring(0,s.indexOf("("))+" Number:"+
                           s.substring(s.indexOf("(")+1, s.indexOf(")")));
    }
}
