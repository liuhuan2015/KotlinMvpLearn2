package com.liuh.kotlinmvplearn2.net.exception

/**
 * Date: 2018/9/26 09:44
 * Description:
 */
object ErrorStatus {

    /**
     * 响应成功
     */
    @JvmField
    val SUCCESS = 0

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常
     */
    @JvmField
    val API_ERROR = 1005

}