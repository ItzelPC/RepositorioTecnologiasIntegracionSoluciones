import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

@EnableWs
@Configuration
public class CalculadoraConfig extends WsConfigurerAdapter{
    @Bean
    public XsdSchema CalculadoraSchema(){
        return new SimpleXsdSchema(new ClassPathResource("Calculadora.xsd"));
    }
    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }
    @Bean (name = "calculadora")
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema calculadoraSchema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("CalculadoraPort");
        wsdl.setLocationUri("/ws/calculadora");
        wsdl.setTargetNamespace("http://www.example.org/calculadora");
        wsdl.setSchema(calculadoraSchema);
        return wsdl;
    }
}