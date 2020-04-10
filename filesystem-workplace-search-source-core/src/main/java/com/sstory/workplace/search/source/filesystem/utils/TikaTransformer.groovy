package com.sstory.workplace.search.source.filesystem.utils

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils
import org.apache.tika.Tika
import org.apache.tika.detect.DefaultDetector
import org.apache.tika.detect.Detector
import org.apache.tika.metadata.Metadata
import org.apache.tika.mime.MimeTypeException
import org.apache.tika.mime.MimeTypes

@CompileStatic
@Slf4j
class TikaTransformer {

    public static List<String> SUPPORTED_FILE_EXTENSIONS = [
            "doc",
            "docx",
            "html",
            "odt",
            "pdf",
            "txt",
            "ppt",
            "pptx",
            "rtf",
            "xls",
            "xlsx"
    ]

    private static final MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes()
    private static final Detector detector = new DefaultDetector(mimeTypes)

    private Tika tika = new Tika()

    TikaResult transform(File input){
        def mediaType = detector.detect(input.newInputStream(), new Metadata()).toString()
        String extension
        try{
            extension = mimeTypes.forName(mediaType).getExtension().replace(".","")
        } catch (MimeTypeException ignored) {
            //nbd
        }
        if(!extension){
            log.trace("Failed to detect extension of '{}' from media type: '{}'.", input.name, mediaType)
            extension = ExtensionMapping.getExtension(mediaType)
            if(!extension){
                log.trace("'{}' not found in the explicit mappings", mediaType)
                extension = FilenameUtils.getExtension(input.name)
            }
        }
        log.trace("Using extension: '{}' for file: {}", extension, input.name)

        def body = ""
        if(extension in SUPPORTED_FILE_EXTENSIONS){
            body = tika.parseToString(input).replaceAll("\\s+", " ")
        }

        return new TikaResult(body, new TikaMetadata(mediaType, extension, input.size().toInteger(), body.size()))

    }
}
