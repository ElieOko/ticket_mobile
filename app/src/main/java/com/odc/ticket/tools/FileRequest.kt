package com.odc.ticket.tools

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.RequestFuture
import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.min

class FileRequest(
    method: Int,
    pathUrl: String,
    private var localParams: Map<String, Any>?,
    listener: Response.Listener<NetworkResponse>,
    errorListener: Response.ErrorListener
) : Request<NetworkResponse>(method, pathUrl, errorListener) {

    private var responseListener: Response.Listener<NetworkResponse>? = listener
    private var futureListener: RequestFuture<Any>? = null

    private var localHeaders: Map<String, String>? = null
    private val divider: String = "--"
    private val ending = "\r\n"
    private val boundary = "ImageRequest${System.currentTimeMillis()}"

    override fun getHeaders(): MutableMap<String, String> {
        val headers = localHeaders?.toMutableMap() ?: super.getHeaders().toMutableMap()
        headers["Accept-Language"] = Locale.getDefault().language
//        val tokenString = SoficomTicket.getInstance().user?.tokenString
//        if(tokenString!=null) {
//            headers["Authorization"] = "Bearer $tokenString"
//        }
        return headers
    }

    override fun getBodyContentType() = "multipart/form-data;boundary=$boundary"

    override fun getBody(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)
        try {
            //val data = getByteData()
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

    /*@Throws(AuthFailureError::class)
    fun getByteData(): Map<String, Any>? {
        return localParams
    }*/

    override fun parseNetworkResponse(response: NetworkResponse?): Response<NetworkResponse> {
        return try {
            Response.success(response, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: NetworkResponse?) {
        responseListener?.onResponse(response)
        futureListener?.onResponse(response)
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