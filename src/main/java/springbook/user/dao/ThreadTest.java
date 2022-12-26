package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ThreadTest extends Thread{

    private CountingConnectionMaker countingConnectionMaker;
    private ApplicationContext context;




    //

    public void setContext(ApplicationContext context) {
        this.context = context;
        this.countingConnectionMaker=context.getBean("connectionMaker", CountingConnectionMaker.class);
    }


    @Override
    public void run()  {
        UserDaoJdbc dao = context.getBean("userDao", UserDaoJdbc.class);
        try {
            dao.get("whiteship");
            dao.get("whiteship");
            dao.get("whiteship");
            dao.get("whiteship");
            dao.get("whiteship");
            dao.get("whiteship");
        } catch (Exception e ) {
            e.printStackTrace();
        }
        System.out.println(countingConnectionMaker.getCounter());
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        for (int i=0;i<10;i++) {
            ThreadTest threadTest = new ThreadTest();
            threadTest.setContext(ac);
            threadTest.start();
        }
        ThreadTest threadTest = new ThreadTest();
        try {
            System.out.println(threadTest.getName());
            threadTest.sleep(10000L);
            CountingConnectionMaker cc = ac.getBean("connectionMaker", CountingConnectionMaker.class);
            System.out.println("last = " + cc.getCounter());
        } catch (Exception e) {

        }

    }
}
