import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Test2{
    public static void main(String[] args) {
        List<String> a = new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
        }};
        System.out.println(StringUtils.join(a,","));
    }
    public static void binaryCode(Integer a){
        Integer b = a;
        StringBuilder r = new StringBuilder();
        String replace = a>=0?"0":"1";
        r.append(replace);
        StringBuilder s = new StringBuilder();
        a=a>0?a:a*-1;
        while(a != 0 && a != 1){
            s.append(a%2==0?0:1);
            a/=2;
        }
        s.append(a);
        String s1 = s.toString();
        for(int i = 30;i > -1;i--){
            r.append(s1.length()>i?s1.substring(i,i+1):replace);
//            r.append((i!=0&&i%4==0)?" ":"");
        }
        System.out.println(r.toString() + " --- " + b);
    }
}