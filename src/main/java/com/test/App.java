package com.test;
/**
 * @author Trangle
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        Doing say = new Say();
//        InvocationHandler handler = new InvocationHandlerUtils(say);
//        Doing proxy = (Doing) Proxy.newProxyInstance(say.getClass().getClassLoader(),say.getClass().getInterfaces(),handler);
//        proxy.say("hello world");

//        for(long i = 0; i < 10000;i++){
//            Users users = new Users();
//            users.setUserName("Trangle");
//            new Thread(new TestRunable(users)).start();
//        }
        for(int i = 0;i < 100;i++){
            new Thread(new TestRunable()).start();
        }
    }
}

class Utils{
    private static long L = 1;

    public  static long getLong(){
        if(L < 100){
            System.out.println(L);
            return L++;
        }
        return 0;
    }
}

class TestRunable implements Runnable{

    public TestRunable(){

    }

    @Override
    public void run() {
        long i = Utils.getLong();
        while(i <= 100){
            i = Utils.getLong();
        }
    }
}


