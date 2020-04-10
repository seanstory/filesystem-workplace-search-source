package com.sstory.workplace.search.source.filesystem.utils

import spock.lang.Shared
import spock.lang.Specification

class TikaTransformerTest extends Specification {

    @Shared
    TikaTransformer tikaTransformer = new TikaTransformer()

    def "tika test"(){
        setup:
        File input = new File("src/test/resources/data/${name}")

        when:
        def output = tikaTransformer.transform(input)

        then:
        output.metadata.extension == extension
        output.text.contains(contained)

        where:
        extension | name | contained
        "pdf" | "certificate.pdf" | "CERTIFICATE OF COMPLETION"
        "png" | "enterprise-search-logo.png" | ""
        "jpg" | "necromancer.jpeg" | ""
        "docx"| "test.docx" | "Curriculum Vitae"
        "doc" | "test.ppt" | "Avoiding the Pitfalls of Bad Slides"
    }
}
