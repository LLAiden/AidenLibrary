package com.aiden.aidenlibrary.utils

import java.io.*
import java.nio.channels.FileChannel

object FileUtil {
    fun createFile(path: String): Boolean {
        val file = File(path)
        if (!file.exists()) {
            if (!file.parentFile?.exists()!!) {
                file.parentFile?.mkdirs()
            }
            return try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
        return true
    }

    /**
     * 创建文件夹
     */
    fun createDir(path: String): Boolean {
        val file = File(path)
        return if (!file.exists()) {
            file.mkdirs()
        } else true
    }

    fun deleteFile(path: String): Boolean {
        val file = File(path)
        var flag = false
        if (file.exists()) {
            flag = if (file.isDirectory) {
                //删除文件夹
                delDir(file)
            } else {
                delFile(file)
            }
        }
        return flag
    }

    /***
     * 删除文件夹
     */
    private fun delDir(fileDir: File): Boolean {
        val files = fileDir.listFiles()
        if (files != null) {
            for (file in files) {
                if (file.isDirectory) {
                    delDir(file)
                } else {
                    delFile(file)
                }
            }
        }
        return fileDir.delete()
    }

    /***
     * 删除文件
     */
    private fun delFile(file: File): Boolean {
        return file.delete()
    }

    /**
     * 文件复制
     */
    fun copyFile(originPath: String, targetPath: String): Boolean {
        var input: FileChannel? = null
        var output: FileChannel? = null
        var flag: Boolean
        try {
            input = FileInputStream(File(originPath)).channel
            output = FileOutputStream(File(targetPath)).channel
            output.transferFrom(input, 0, input.size())
            flag = true
        } catch (e: Exception) {
            flag = false
            e.printStackTrace()
        } finally {
            input?.close()
            output?.close()
        }
        return flag
    }

    /**
     * 文件移动
     */
    fun moveFile(originPath: String, targetPath: String): Boolean {
        val file = File(originPath)
        return file.renameTo(File(targetPath))
    }

    /**
     * 文件改名
     */
    fun rename(originPath: String, targetPath: String): Boolean {
        return moveFile(
            originPath,
            targetPath
        )
    }

    /**
     * 从文件中读取数据
     */
    fun readTextByFile(originPath: String): String {
        val stringBuffer = StringBuffer()
        try {
            BufferedReader(FileReader(originPath)).use { bufferedReader ->
                var line: String?
                while (true) {
                    line = bufferedReader.readLine()
                    if (line == null) {
                        break
                    }
                    stringBuffer.append(line)
                        .append("\n")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuffer.toString()
    }

    fun write2File(targetPath: String, content: String, isAppend: Boolean = false) {
        val file = File(targetPath)
        if (!file.exists()) {
            if (createFile(file.absolutePath)) {
                println("create file " + file.absolutePath)
            }
        }
        try {
            BufferedWriter(FileWriter(file, isAppend))
                .use { bufferedWriter -> bufferedWriter.write(content, 0, content.length) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}