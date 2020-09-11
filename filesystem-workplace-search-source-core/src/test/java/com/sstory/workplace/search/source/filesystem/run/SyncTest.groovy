package com.sstory.workplace.search.source.filesystem.run;

import com.seanjstory.workplace.search.sdk.run.Sync
import org.junit.Rule
import org.junit.contrib.java.lang.system.ExpectedSystemExit
import org.junit.contrib.java.lang.system.internal.CheckExitCalled
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import spock.lang.Shared
import spock.lang.Specification

class SyncTest extends Specification {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none()

    @Shared
    private ClientAndServer mockServer;

    def setupSpec() {
        mockServer = ClientAndServer.startClientAndServer(3002)
    }

    def cleanupSpec() {
        mockServer.stop()
    }

    def "test the sync"(){
        setup:
        def yamlPath = "src/test/resources/test.yml"
        def contentSourceKey = "5e8f5266f74c321dae6e5548"
        def sourceName = "filesystem"

        def req = HttpRequest.request().withMethod("POST").withPath("/api/ws/v1/sources/${contentSourceKey}/documents/bulk_create.json")
        def res = HttpResponse.response().withStatusCode(200).withBody('{"very":"nice"}').withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
        mockServer.when(req).respond(res)

        when:
        exit.expectSystemExit()
        Sync.main(yamlPath, sourceName)

        then:
        CheckExitCalled e = thrown()
        e.status == 0
    }
}
