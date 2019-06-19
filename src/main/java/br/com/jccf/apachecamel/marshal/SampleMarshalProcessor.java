package br.com.jccf.apachecamel.marshal;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.StringTokenizer;

public class SampleMarshalProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String value = exchange.getIn().getBody(String.class);
        StringTokenizer tokenizer = new StringTokenizer(value, ",");

        Person person = new Person();
        while (tokenizer.hasMoreElements()) {
            person.setName(tokenizer.nextElement().toString());
            person.setAge(tokenizer.nextElement().toString());
        }

        exchange.getIn().setBody(person);
    }
}
