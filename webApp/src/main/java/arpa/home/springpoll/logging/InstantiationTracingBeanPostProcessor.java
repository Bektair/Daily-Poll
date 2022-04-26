package arpa.home.springpoll.logging;

import org.springframework.beans.factory.config.BeanPostProcessor;

public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean; // we could potentially return any object reference here...
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) {
    System.out.println("Bean '" + beanName + "' created : " + bean.toString());
    return bean;
	}
	
	
}
