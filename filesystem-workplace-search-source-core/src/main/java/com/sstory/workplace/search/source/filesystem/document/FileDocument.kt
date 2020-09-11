package com.sstory.workplace.search.source.filesystem.document

import com.seanjstory.workplace.search.sdk.api.DocumentBase
import com.sstory.workplace.search.source.filesystem.utils.TikaResult
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class FileDocument(val file: File, val tikaResult: TikaResult): DocumentBase(file.absolutePath, tikaResult.text) {

    private companion object {
        val format: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }

    override fun getMetadata(): MutableMap<String, Any> {
        val attributes = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
        return mutableMapOf(
                "name" to file.name,
                "mime_type" to tikaResult.metadata.mimeType,
                "extension" to tikaResult.metadata.extension,
                "size" to tikaResult.metadata.origSize,
                "text_size" to tikaResult.metadata.textSize,
                "url" to file.toURI().toURL().toString().replace("file:", "file://"),
                "path" to file.absolutePath,
                "last_modified" to Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()).format(format),
                "created_at" to attributes.creationTime().toInstant().atZone(ZoneId.systemDefault()).format(format),
                "is_symlink" to attributes.isSymbolicLink
        )
    }

}
