package com.liuh.kotlinmvplearn2.net.exception

/**
 * Date: 2018/9/26 09:49
 * Description:
 */
class ApiException : RuntimeException {

    private var code: Int? = null

    constructor(message: String?) : super(Throwable(message))

    constructor(cause: Throwable?, code: Int?) : super(cause) {
        this.code = code
    }


}