package com.liuh.kotlinmvplearn2.mvp.model.bean

import java.io.Serializable

/**
 * Date: 2018/10/15 18:06
 * Description:分类 Bean
 */
data class CategoryBean(val id: Long, val name: String, val description: String, val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable