package com.sstory.workplace.search.source.filesystem.sources

import com.seanjstory.workplace.search.sdk.api.Source
import com.seanjstory.workplace.search.sdk.api.Yielder
import com.sstory.workplace.search.source.filesystem.document.FileDocument
import com.sstory.workplace.search.source.filesystem.utils.TikaResult
import com.sstory.workplace.search.source.filesystem.utils.TikaTransformer
import groovy.io.FileType
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.nio.file.Files
import java.util.regex.Pattern

@CompileStatic
@Slf4j
class FilesystemSource implements Source<FileDocument> {

    private final File root
    private Pattern pattern
    private TikaTransformer tikaTransformer

    FilesystemSource(String rootPath){
        this(rootPath, null)
    }

    FilesystemSource(String rootPath, String pattern){
        root = new File(rootPath)
        if(!root.exists() || !root.isDirectory() || !Files.isReadable(root.toPath())){
            throw new IllegalArgumentException("The root for ${this.class.simpleName} must exist, be a directory, and be readable")
        }
        if(pattern){
            this.pattern = Pattern.compile(pattern)
        }
        tikaTransformer = new TikaTransformer()
    }

    @Override
    Iterator<FileDocument> getDocuments() {
        List<File> files = []
        Map<String, Object> options = ["type": FileType.FILES, "nameFilter": pattern] as Map<String, Object>
        root.traverse(options,{
            files << it
        })
        log.info("Processing {} files found under {} matching pattern: {}", files.size(), root.absolutePath, pattern ? pattern.toString() : "*")
        Iterator<File> fileIt = files.iterator()
        //TODO add archive support

        return new Yielder<FileDocument>(){

            @Override
            protected void yieldNextCore() {
                if (fileIt.hasNext()){
                    File current = fileIt.next()
                    log.debug("Processing file: {}", current.absolutePath)
                    TikaResult tikaResult = tikaTransformer.transform(current)
                    FileDocument output = new FileDocument(current, tikaResult)
                    log.trace(
                            "Yielding document of text size: '{}', extension: '{}', type: '{}' - with id: '{}', ",
                            tikaResult.metadata.textSize,
                            tikaResult.metadata.extension,
                            tikaResult.metadata.mimeType,
                            output.id
                    )
                    yieldReturn(output)
                } else {
                    yieldBreak()
                }

            }

        }.iterator()
    }
}
