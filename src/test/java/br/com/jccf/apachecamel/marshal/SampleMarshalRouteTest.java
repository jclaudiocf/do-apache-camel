package br.com.jccf.apachecamel.marshal;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SampleMarshalRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new SampleMarshalRoute();
    }

    @Test
    public void marshalRoute() throws InterruptedException {
        //String expected = "<?xml version='1.0' encoding='UTF-8'?><br.com.jccf.apachecamel.marshal.Person><name>paul</name><age>26</age></br.com.jccf.apachecamel.marshal.Person>";
        String expected = "<?xml version='1.0' encoding='UTF-8'?><person><name>paul</name><age>26</age></person>";
        String input = "paul,26";

        MockEndpoint mock = getMockEndpoint("mock:output");
        mock.expectedBodiesReceived(expected);

        template.sendBody("direct:marshalInput", input);

        assertMockEndpointsSatisfied();
    }
}