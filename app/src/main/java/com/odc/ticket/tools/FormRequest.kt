package com.odc.ticket.tools

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import java.io.*
import java.nio.charset.Charset
import kotlin.math.min

abstract class FormRequest<T>(private val clazz: Class<T>, method: Int, pathUrl: String, private var localParams: Map<String, Any>?, listener: Response.Listener<T>, errorListener: Response.ErrorListener) : Request<T>(method, pathUrl, errorListener) {
    private var responseListener: Response.Listener<T>? = listener

    private val divider: String = "--"
    private val ending = "\r\n"
    private val boundary = "ImageRequest${System.currentTimeMillis()}"

    override fun getBodyContentType() = "multipart/form-data;boundary=$boundary"

    override fun getBody(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)
        try {
            val params = HashMap<String,String>()
            val files = HashMap<String,FileDataPart>()
            localParams?.let{items->
                for ((k, v) in items){
                    when(v){
                        is FileDataPart -> {
                            files[k]=v
                        }
                        is String -> {
                            params[k]=v
                        }
                    }
                }
            }
            if (params.isNotEmpty()) {
                processParams(dataOutputStream, params, paramsEncoding)
            }
            if(files.isNotEmpty()){
                processData(dataOutputStream, files)
            }
            dataOutputStream.writeBytes(divider + boundary + divider + ending)
            return byteArrayOutputStream.toByteArray()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return super.getBody()
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            //Response.success(response, HttpHeaderParser.parseCacheHeaders(response))
            val json = String(response?.data ?: ByteArray(0), Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            Response.success(Gson().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: T) {
        responseListener?.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        errorListener?.onErrorResponse(error)
    }

    @Throws(IOException::class)
    private fun processParams(dataOutputStream: DataOutputStream, params: Map<String, String>, encoding: String) {
        try {
            params.forEach {
                dataOutputStream.writeBytes(divider + boundary + ending)
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"${it.key}\"$ending")
                dataOutputStream.writeBytes(ending)
                dataOutputStream.writeBytes(it.value + ending)
            }
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("Unsupported encoding not supported: $encoding with error: ${e.message}", e)
        }
    }

    @Throws(IOException::class)
    private fun processData(dataOutputStream: DataOutputStream, data: Map<String, FileDataPart>) {
        data.forEach {
            val dataFile = it.value
            dataOutputStream.writeBytes("$divider$boundary$ending")
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"${it.key}\"; filename=\"${dataFile.fileName}\"$ending")
            if (dataFile.type.trim().isNotEmpty()) {
                dataOutputStream.writeBytes("Content-Type: ${dataFile.type}$ending")
            }
            dataOutputStream.writeBytes(ending)
            val fileInputStream = ByteArrayInputStream(dataFile.data)
            var bytesAvailable = fileInputStream.available()
            val maxBufferSize = 1024 * 1024
            var bufferSize = min(bytesAvailable, maxBufferSize)
            val buffer = ByteArray(bufferSize)
            var bytesRead = fileInputStream.read(buffer, 0, bufferSize)
            while (bytesRead > 0) {
                dataOutputStream.write(buffer, 0, bufferSize)
                bytesAvailable = fileInputStream.available()
                bufferSize = min(bytesAvailable, maxBufferSize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize)
            }
            dataOutputStream.writeBytes(ending)
        }
    }

    class FileDataPart(var fileName: String?, var data: ByteArray, var type: String)
}