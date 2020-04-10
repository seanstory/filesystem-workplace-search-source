package com.sstory.workplace.search.source.filesystem.utils

import spock.lang.Specification

class ExtensionMappingTest extends Specification {

    def "test map reversal"(){
        expect:
        ExtensionMapping.mimeToExtensionMap.get("image/jpeg") == "jpg"
    }
}
