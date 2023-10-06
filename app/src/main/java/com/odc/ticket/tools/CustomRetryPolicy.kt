package com.odc.ticket.tools

import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError

class CustomRetryPolicy(initialTimeoutMs: Int, maxNumRetries: Int, backoffMultiplier: Float) : RetryPolicy {
    companion object {
        private const val Tag = "CustomRetryPolicy"
        private const val Unauthorized = 401
    }

    private var defaultRetryPolicy = DefaultRetryPolicy(initialTimeoutMs, maxNumRetries, backoffMultiplier)

    override fun getCurrentTimeout(): Int {
        return defaultRetryPolicy.currentTimeout
    }

    override fun getCurrentRetryCount(): Int {
        return defaultRetryPolicy.currentRetryCount
    }

    @Throws(VolleyError::class)
    override fun retry(error: VolleyError?) {
        if(error?.networkResponse?.statusCode == Unauthorized){
            Log.d(Tag, "unauthorized, don't retry")
            throw error
        } else {
            defaultRetryPolicy.retry(error)
        }
    }
}