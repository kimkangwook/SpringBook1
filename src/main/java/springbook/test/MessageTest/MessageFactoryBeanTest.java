package springbook.test.MessageTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.learningtest.message.Message;
import springbook.learningtest.message.MessageFactoryBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring/applicationContext.xml")
public class MessageFactoryBeanTest {
    @Autowired
    ApplicationContext ac;

    @Test
    public void test1() throws Exception{
        MessageFactoryBean bean = ac.getBean(MessageFactoryBean.class);
        Message message1 = bean.getObject();
        Message message2 = bean.getObject();
        System.out.println(message1);
        System.out.println(message2);

        Message message3 = ac.getBean(Message.class);
        Message message4 = ac.getBean(Message.class);
        System.out.println(message3);
        System.out.println(message4);
    }

    @Test
    public void test2() throws Exception{
        ApplicationContext ac = new AnnotationConfigApplicationContext(Cofig.class);
        Message message1 = ac.getBean(Message.class);
        Message message2 = ac.getBean(Message.class);
        System.out.println(message1);
        System.out.println(message2);
    }

    static class Cofig {
        @Bean
        public Message message() throws Exception{
            MessageFactoryBean mfb = new MessageFactoryBean();

                return mfb.getObject();

        }


    }




}
