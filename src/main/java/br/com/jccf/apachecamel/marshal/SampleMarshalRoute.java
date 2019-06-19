package br.com.jccf.apachecamel.marshal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;

import java.util.HashMap;
import java.util.Map;

public class SampleMarshalRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:marshalInput")
                .log("Body message is ${body}")
                .process(new SampleMarshalProcessor())
//                .marshal().xstream()
                .marshal(populateXStreamDef())
                .to("log:?level=INFO&showBody=True")
                .to("mock:output");
    }

    private XStreamDataFormat populateXStreamDef() {
        XStreamDataFormat dataFormat = new XStreamDataFormat();

        Map<String, String> aliases = new HashMap<>();
        aliases.put("person", Person.class.getName());

        dataFormat.setAliases(aliases);

        return dataFormat;
    }

}
