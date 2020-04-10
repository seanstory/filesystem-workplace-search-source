package com.sstory.workplace.search.source.filesystem.utils

data class TikaMetadata(val mimeType: String, val extension: String, val origSize: Int, val textSize: Int)
data class TikaResult(val text: String, val metadata: TikaMetadata )
