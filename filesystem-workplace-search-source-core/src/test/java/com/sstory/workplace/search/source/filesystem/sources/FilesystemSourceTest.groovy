package com.sstory.workplace.search.source.filesystem.sources

import com.sstory.workplace.search.source.filesystem.document.FileDocument
import groovy.json.JsonOutput
import spock.lang.Specification

class FilesystemSourceTest extends Specification {

    def "test crawling a dir"(){
        setup:
        FilesystemSource source = new FilesystemSource("src/test/resources/data/")

        when:
        List<FileDocument> output = source.getDocuments().collect()
        println JsonOutput.prettyPrint(JsonOutput.toJson(output.collect{it.toMap()}))

        then:
        output.size() == 7
        output.every{new File(it.id).exists()}
        output.findAll{it.body.isEmpty()}.size() == 2
    }
}
