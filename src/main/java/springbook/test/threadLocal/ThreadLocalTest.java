package springbook.test.threadLocal;

public class ThreadLocalTest extends Thread{

    private People people;

    public void setPeople(People people) {
        this.people = people;
    }

    public void start() {
        people.instruct();
    }

    public static void main(String[] args) {
        String[] jobs = {"선생님", "군인", "미용사", "개발자"};

        for (int i=0;i<3;i++) {
            ThreadLocalTest tl = new ThreadLocalTest();
            tl.setPeople(new People(jobs[i]));
            tl.start();
        }
    }



     static class People {
        String job;

         public People(String job) {
             this.job = job;
         }

         public void instruct() {
            Context.local.set(job);

             Post post = new Post();
             post.instruct();

             Context.local.remove();
        }
    }

    static class Post {

        public void instruct() {
            System.out.println("저는 "+Context.local.get()+"였습니다.");

            Future future = new Future();
            future.instruct();
        }
    }

    static class Future {

        public void instruct() {
            System.out.println("저는 "+Context.local.get()+"이 될것입니다.");
        }
    }

     static class Context {
         static ThreadLocal<String> local = new ThreadLocal();
    }


}
