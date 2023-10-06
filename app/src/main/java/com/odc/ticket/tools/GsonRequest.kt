package com.odc.ticket.tools

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.odc.ticket.app.SoficomTicket
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

class GsonRequest<T>(private val clazz: Class<T>, method : Int, pathUrl: String, private val localHeaders: MutableMap<String, String>? = null, private val localBody: Any? = null, private val listener: Response.Listener<T>, errorListener: Response.ErrorListener) : Request<T>(method, pathUrl, errorListener) {

    override fun getHeaders(): MutableMap<String, String> {
        val headers = localHeaders?.toMutableMap() ?: super.getHeaders().toMutableMap()
        headers["Accept-Language"] = Locale.getDefault().language
        val tokenString = SoficomTicket.getInstance().user?.tokenString
        if(tokenString!=null) {
            headers["Authorization"] = "Bearer $tokenString"
        }
        return headers
    }

    override fun getBody(): ByteArray {
        return if(localBody!=null) { Gson().toJson(localBody).toByteArray()} else super.getBody()
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            val json = String(response?.data ?: ByteArray(0), Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
            Response.success(Gson().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: JsonSyntaxException) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: T) = listener.onResponse(response)
}