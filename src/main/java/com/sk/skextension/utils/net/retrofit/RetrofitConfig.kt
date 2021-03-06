package com.sk.skextension.utils.net.retrofit

import java.net.Proxy

/**
 * retrofit配置接口
 */
abstract class RetrofitConfig(
    /**
     * 获取基础host
     *
     * @return
     */
    val baseUrl: String
) {
    /**
     * 标签
     */
    val TAG: String = this.javaClass.simpleName

    /**
     * 获取连接超时时间
     *
     * @return
     */
    fun connectTimeout(): Long {
        return DEFAULT_CONNECT_TIMEOUT
    }

    /**
     * 获取读取超时时间
     *
     * @return
     */
    fun readTimeout(): Long {
        return DEFAULT_READ_TIMEOUT
    }

    /**
     * 获取写入超时时间
     *
     * @return
     */
    fun writeTimeout(): Long {
        return DEFAULT_WRITE_TIMEOUT
    }

    /**
     * 获取代理ip
     *
     * @return
     */
    fun proxyIPAddr(): String {
        return ""
    }

    /**
     * 获取代理端口
     *
     * @return
     */
    fun proxyPort(): Int {
        return 0
    }

    /**
     * 获取代理类型
     *
     * @return
     */
    fun proxyType(): Proxy.Type {
        return Proxy.Type.DIRECT
    }

    /**
     * 代理用户名
     *
     * @return
     */
    fun proxyUserName(): String {
        return ""
    }

    /**
     * 代理密码
     *
     * @return
     */
    fun proxyPassword(): String {
        return ""
    }

    /**
     * 默认头部
     *
     * @return
     */
    open fun defaultHeaders(): Map<String, String> {
        return emptyMap()
    }

    /**
     * 默认参数
     *
     * @returns
     */
    open fun defaultParams(): Map<String, String> {
        return emptyMap()
    }

    /**
     * token是否需要更新
     */
    abstract fun isTokenShouldUpdate(): Boolean

    companion object {
        /**
         * 默认连接超时
         */
        const val DEFAULT_CONNECT_TIMEOUT = 10 * 1000L

        /**
         * 默认读取超时
         */
        const val DEFAULT_READ_TIMEOUT = 10 * 1000L

        /**
         * 默认写入超时
         */
        const val DEFAULT_WRITE_TIMEOUT = 10 * 1000L
    }
}