package com.example.cleanarchitectureex.data.datasource.remote

import retrofit2.Response

sealed class ApiResult<out T> {
    /**
     * out - ApiResult가 T타입의 데이터를 반환할 수 있지만, 매개변수로 받는 함수에는 사용할수 없다.
     *     - 타입 안정성을 보장
     *     - 하위 타입의 객체를 해당 제네릭 클래스의 인스턴스로 안전하게 사용할 수 있게 함
     */
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val msg : String) : ApiResult<Nothing>()
}