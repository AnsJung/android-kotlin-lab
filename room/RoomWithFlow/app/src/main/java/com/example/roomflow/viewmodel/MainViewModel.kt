package com.example.roomflow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomflow.database.TextDatabase
import com.example.roomflow.database.entity.TextEntity
import com.example.roomflow.repository.DataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repositoryImpl: DataRepositoryImpl) :
    ViewModel() {

    private var _text = MutableLiveData("")
    val text: LiveData<String> = _text

    fun collectData() {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryImpl.getAllDataFlow().collect {
                /**
                 * collect - 수집, 고정된 간격으로 앞의 함수를 새로고침하고 요청 결과를 내보내는 생산자를 트리거
                 * while(true) 루프로 항상 활성상태가 유지되므로 뷰모델이 삭제되면  scope가 종료되어 스트림을 종료
                 */
                withContext(Dispatchers.Main) {
                    _text.value = it.ifEmpty {
                        ""
                    }.toString()
                }
            }
        }
    }

    fun insertData(text: TextEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryImpl.insert(text)
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryImpl.deleteAll()
        }
    }
}