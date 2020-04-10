package com.sstory.workplace.search.source.filesystem.filesystem.sources;

import com.sstory.workplace.search.sdk.api.Source;
import com.sstory.workplace.search.sdk.api.Yielder;
import com.sstory.workplace.search.source.filesystem.filesystem.document.HelloWorldDocument;

import java.time.Instant;
import java.util.Iterator;

public class HelloWorldSource implements Source<HelloWorldDocument> {

    private final String greeting;

    public HelloWorldSource(){
        greeting = "hello, world!";
    }

    public HelloWorldSource(String greeting){
        this.greeting = greeting;
    }

    @Override
    public Iterator<HelloWorldDocument> getDocuments() {

        return new Yielder<HelloWorldDocument>(){
            boolean first =true;
            @Override
            protected void yieldNextCore() {
                if (!first){
                    yieldBreak();
                    return;
                }
                String id = String.valueOf(Instant.now().toEpochMilli());
                String body = greeting;
                yieldReturn(new HelloWorldDocument(id, body));
                first=false;
            }

        }.iterator();
    }
}
