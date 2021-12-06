package com.sk.skextension.utils.net.retrofit

import com.sk.skextension.utils.net.retrofit.JsonUtil.formatJson
import com.sk.skextension.utils.net.retrofit.JsonUtil.decodeUnicode
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory

/**
 * 日志拦截器
 */
class HttpLogger : HttpLoggingInterceptor.Logger {
    /**
     * 请求信息全部日志
     */
    private val mMessage = StringBuffer()
    override fun log(message: String) {
        // 请求或者响应开始
        var mresultMessage = message
        if (mresultMessage.startsWith("--> POST") || mresultMessage.startsWith("--> GET")) {
            mMessage.setLength(0)
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if (mresultMessage.startsWith("{") && mresultMessage.endsWith("}")
            || mresultMessage.startsWith("[") && mresultMessage.endsWith("]")
        ) {
            mresultMessage = formatJson(decodeUnicode(mresultMessage))
        }
        mMessage.append(
            """
    $mresultMessage
    
    """.trimIndent()
        )
        // 响应结束，打印整条日志
        if (mresultMessage.startsWith("<-- END HTTP")) {
            LoggerFactory.getLogger(HttpLogger::class.java.simpleName).info(mMessage.toString())
            mMessage.delete(0, mMessage.length)
        }
    }
}