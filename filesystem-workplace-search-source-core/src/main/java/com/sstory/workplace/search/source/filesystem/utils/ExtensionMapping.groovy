package com.sstory.workplace.search.source.filesystem.utils

import groovy.transform.CompileStatic

import java.util.stream.Collectors

@CompileStatic
class ExtensionMapping {

    private static final Map<String,List<String>> extensionToMimeMap = [
            "" : [
                    "application/octet-stream"
            ],
            "doc" : [
                    "application/x-tika-msoffice",
                    "application/msword"
            ],
            "docx" : [
                    "application/x-tika-ooxml",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.template",
                    "application/vnd.ms-word.template.macroenabled.12",
                    "application/vnd.ms-word.document.macroenabled.12"
            ],
            "eml" : [
                    "message/rfc822"
            ],
            "gif" : [
                    "image/gif"
            ],
            "html" : [
                    "text/html"
            ],
            "jpg" : [
                    "image/jpeg"
            ],
            "mpp" : [
                    "application/vnd.ms-project"
            ],
            "odf" : [
                    "application/x-vnd.oasis.opendocument.graphics-template",
                    "application/vnd.sun.xml.writer", "application/x-vnd.oasis.opendocument.text",
                    "application/x-vnd.oasis.opendocument.text-web",
                    "application/x-vnd.oasis.opendocument.spreadsheet-template",
                    "application/vnd.oasis.opendocument.formula-template",
                    "application/vnd.oasis.opendocument.presentation",
                    "application/vnd.oasis.opendocument.image-template",
                    "application/x-vnd.oasis.opendocument.graphics",
                    "application/vnd.oasis.opendocument.chart-template",
                    "application/vnd.oasis.opendocument.presentation-template",
                    "application/x-vnd.oasis.opendocument.image-template",
                    "application/vnd.oasis.opendocument.formula",
                    "application/x-vnd.oasis.opendocument.image",
                    "application/vnd.oasis.opendocument.spreadsheet-template",
                    "application/x-vnd.oasis.opendocument.chart-template",
                    "application/x-vnd.oasis.opendocument.formula",
                    "application/vnd.oasis.opendocument.spreadsheet",
                    "application/vnd.oasis.opendocument.text-web",
                    "application/vnd.oasis.opendocument.text-template",
                    "application/vnd.oasis.opendocument.text",
                    "application/x-vnd.oasis.opendocument.formula-template",
                    "application/x-vnd.oasis.opendocument.spreadsheet",
                    "application/x-vnd.oasis.opendocument.chart",
                    "application/vnd.oasis.opendocument.text-master",
                    "application/x-vnd.oasis.opendocument.text-master",
                    "application/x-vnd.oasis.opendocument.text-template",
                    "application/vnd.oasis.opendocument.graphics",
                    "application/vnd.oasis.opendocument.graphics-template",
                    "application/x-vnd.oasis.opendocument.presentation",
                    "application/vnd.oasis.opendocument.image",
                    "application/x-vnd.oasis.opendocument.presentation-template",
                    "application/vnd.oasis.opendocument.chart"
            ],
            "pdf" : [
                    "application/pdf"
            ],
            "png" : [
                    "image/png"
            ],
            "ppt" : [
                    "application/vnd.ms-powerpoint"
            ],
            "pptx" : [
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                    "application/vnd.ms-powerpoint.presentation.macroenabled.12",
                    "application/vnd.openxmlformats-officedocument.presentationml.template",
                    "application/vnd.ms-powerpoint.slideshow.macroenabled.12",
                    "application/vnd.ms-powerpoint.addin.macroenabled.12",
                    "application/vnd.openxmlformats-officedocument.presentationml.slideshow"
            ],
            "pub" : [
                    "application/x-mspublisher"
            ],
            "rtf" : [
                    "message/richtext",
                    "text/richtext",
                    "text/rtf",
                    "application/rtf"
            ],
            "svg" :[
                    "image/svg+xml"
            ],
            "txt" : [
                    "text/plain",
                    "message/news"
            ],
            "xlr" : [
                    "application/x-tika-msworks-spreadsheet"
            ],
            "xlsx" : [
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
                    "application/vnd.ms-excel.addin.macroenabled.12",
                    "application/vnd.ms-excel.template.macroenabled.12",
                    "application/vnd.ms-excel.sheet.macroenabled.12"
            ],
            "xls" : [
                    "application/vnd.ms-excel.sheet.3",
                    "application/vnd.ms-excel.sheet.2",
                    "application/vnd.ms-excel.workspace.3",
                    "application/vnd.ms-excel.workspace.4",
                    "application/vnd.ms-excel.sheet.4"
            ],
            "xml" : [
                    "text/xml",
                    "application/xml"
            ]
    ]

    private static class Pair{
        String first;
        String second;
    }

    private static final Map<String, String> mimeToExtensionMap = extensionToMimeMap.entrySet().collect { Map.Entry<String, List<String>> it ->
            it.value.collect { mime ->
                    new Pair([first: mime, second: it.key])
            }
    }.flatten().stream().collect(Collectors.toMap({ ((Pair)it).first }, { ((Pair)it).second }))

    static String getExtension(String mimeType){
        return mimeToExtensionMap.get(mimeType)
    }

}

